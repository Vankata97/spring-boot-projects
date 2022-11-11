package codeacademy.loanrequest.controllertests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/QueriesForTest.sql"})
class FileControllerTest
{

  private static final Logger  logger = LogManager.getLogger(FileControllerTest.class);
  @Autowired
  private              MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpload() throws Exception
  {

    MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", "".getBytes());

    mockMvc
        .perform(multipart("/svc/upload")
            .file(file)
            .param("id", "1"))
        .andExpect(status().is(200));

    logger.info("Upload test successful!");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUploadIfNotPdf() throws Exception
  {

    MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "".getBytes());

    mockMvc
        .perform(multipart("/svc/upload")
            .file(file)
            .param("id", "1"))
        .andExpect(status().isBadRequest());

    logger.info("Test upload if not PDF passed successful!");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUploadLargeFile() throws Exception
  {
    byte[] bytes = new byte[1024 * 1024 * 10];

    MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", bytes);

    mockMvc
        .perform(multipart("/svc/upload")
            .file(file)
            .param("id", "1"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("File too large")));
    logger.info("Test for stop upload large file passed successful!");
  }
}
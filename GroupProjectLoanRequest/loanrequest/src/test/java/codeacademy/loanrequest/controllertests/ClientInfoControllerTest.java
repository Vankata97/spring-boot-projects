package codeacademy.loanrequest.controllertests;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

@Transactional
@AutoConfigureMockMvc
@Sql(scripts = {"/QueriesForTest.sql"})
public class ClientInfoControllerTest
{


  private static final Logger logger = LogManager.getLogger(ClientInfoControllerTest.class);

  @Autowired
  private MockMvc mockMvc;


  @Test
  @WithMockUser(value = "Petar", roles = "USER")
  public void testFindClientData() throws Exception
  {
    mockMvc
        .perform(get("/svc/clientinfo")
            .contentType(MediaType.APPLICATION_JSON)
            .queryParam("id", String.valueOf(15)))
        .andDo(print())
        .andExpect(status().isOk());

    logger.info("Test Find Client Data passed successfully!");
  }
  @Test
  @WithMockUser(value = "Petar", roles = "USER")
  public void testFindClientDataWrongId() throws Exception
  {
    mockMvc
        .perform(get("/svc/clientinfo")
            .contentType(MediaType.APPLICATION_JSON)
            .queryParam("id", String.valueOf(11)))
        .andDo(print())
        .andExpect(status().isBadRequest());

    logger.info("Test Find Client Data passed successfully!");
  }
}

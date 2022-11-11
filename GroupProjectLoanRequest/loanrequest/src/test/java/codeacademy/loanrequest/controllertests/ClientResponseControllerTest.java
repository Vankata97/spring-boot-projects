package codeacademy.loanrequest.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codeacademy.loanrequest.dto.ClientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/QueriesForTest.sql"})
class ClientResponseControllerTest
{

  private static final Logger  logger = LogManager.getLogger(FinancialStatusControllerTest.class);
  @Autowired
  private              MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  public void testCreateClientResponse() throws Exception
  {
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setAmount(new BigDecimal(15000));
    clientResponse.setMonths(70);

    mockMvc
        .perform(post("/svc/response")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clientResponse))
            .queryParam("id", String.valueOf(1)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test successful");
  }

  @Test
  public void testRejectClientResponse() throws Exception
  {
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setAmount(new BigDecimal(7000));
    clientResponse.setMonths(70);

    mockMvc
        .perform(post("/svc/response")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clientResponse))
            .queryParam("id", String.valueOf(1)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test reject client response passed successful");
  }
  @Test
  public void testReject2ClientResponse() throws Exception
  {
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setAmount(new BigDecimal(17000));
    clientResponse.setMonths(11);

    mockMvc
        .perform(post("/svc/response")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clientResponse))
            .queryParam("id", String.valueOf(1)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test reject client response because of wrong data passes successful");
  }
}
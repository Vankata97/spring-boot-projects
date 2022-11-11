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
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/QueriesForTest.sql"})
public class BankOfferControllerTest
{

  private static final Logger  logger = LogManager.getLogger(BankOfferControllerTest.class);
  @Autowired
  private              MockMvc mockMvc;


  @Test
  @WithMockUser(roles = "USER")
  public void testGetBankOffer() throws Exception
  {
    mockMvc
        .perform(get("/svc/bankoffer")
            .contentType(MediaType.APPLICATION_JSON)
            .queryParam("id", String.valueOf(2)))
        .andExpect(status().isOk())
        .andDo(print());

    logger.info("Get BankOffer test passed successful!");
  }

  @Test
  @WithMockUser(roles = "USER")
  public void testGetBankOfferNoUserID() throws Exception
  {
    mockMvc
        .perform(get("/svc/bankoffer").queryParam("id","11"))
        .andExpect(status().isBadRequest())
        .andDo(print());
    logger.info("There is no such user!");
  }
}



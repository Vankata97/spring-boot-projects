package codeacademy.loanrequest.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codeacademy.loanrequest.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ClientControllerTest
{

  private static final Logger logger = LogManager.getLogger(FinancialStatusControllerTest.class);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  //testing create
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateClient() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("juanantonio@goodmail.es");
    mockMvc
        .perform(post("/svc/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test Create Client passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateClientWithWrongEmail() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("juanantonio.goodmail.es");
    mockMvc
        .perform(post("/svc/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Create Client With Wrong Email passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateClientWithAlreadyExistingEmail() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("IHadjiev97@gmail.com");
    mockMvc
        .perform(post("/svc/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Create Client With Already Existing Email passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateClientWithNoArgs() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("");
    mockMvc
        .perform(post("/svc/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Create Client With No Arguments passed successful");
  }

  //testing update
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateClient() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("juanantonio@goodmail.es");
    mockMvc
        .perform(put("/svc/client/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isOk())
        .andDo(print());

    logger.info("Test Update Client passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateClientWithWrongEmail() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("juanantonio.goodmail.es");
    mockMvc
        .perform(put("/svc/client/{id}", 18)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Update Client With Wrong Email passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateClientWithExistingEmail() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("IHadjiev97@gmail.com");
    mockMvc
        .perform(put("/svc/client/{id}", 3)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Update Client With Existing Email passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateClientWithWithNoArgs() throws Exception
  {
    Client client = new Client();
    client.setE_mail_rc("");
    mockMvc
        .perform(put("/svc/client/{id}", 18)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Update Client With No Arguments passed successful");
  }

  //testing delete
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteClient() throws Exception
  {
    mockMvc
        .perform(delete("/svc/client/{id}", 18)
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isResetContent())
        .andDo(print());

    logger.info("Test Delete Client passed successful");
  }

  //testing get by id
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetClientById() throws Exception
  {
    mockMvc
        .perform(get("/svc/client/{id}", 1))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id_client").value(1))
        .andDo(print());

    logger.info("Test Get Client By Id passed successful");
  }

  //TODO: check the test
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetClientByWrongId() throws Exception
  {
    mockMvc
        .perform(get("/svc/client/{id}", 97))
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Get Client By Wrong Id passed successful");
  }

  //testing get all clients
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetAllClients() throws Exception
  {
    mockMvc
        .perform(get("/svc/client"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    logger.info("Test Get All Clients passed successful");
  }
}


package codeacademy.loanrequest.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codeacademy.loanrequest.model.PersonalInformation;
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
public class PersonalInformationControllerTest
{

  private static final Logger logger = LogManager.getLogger(FinancialStatusControllerTest.class);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  //testing create
  @Test
  public void testCreatePersonalInformation() throws Exception
  {
    PersonalInformation personalInformation = new PersonalInformation();
    personalInformation.setFirstName("I'm");
    personalInformation.setLastName("Batman");
    personalInformation.setPhone("0869696969");
    personalInformation.setAddress("Gotham");
    personalInformation.setCitizenship("none");
    personalInformation.setAge(29);
    personalInformation.setMarital_status("single");
    personalInformation.setChildren(0);

    mockMvc
        .perform(post("/svc/personalinformation")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(personalInformation))
            .queryParam("id", String.valueOf(1)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test Create Personal Information passed successful");
  }

  //testing update
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdatePersonalInformation() throws Exception
  {
    PersonalInformation personalInformation = new PersonalInformation();
    personalInformation.setFirstName("Katya");
    personalInformation.setLastName("Goloto");
    personalInformation.setPhone("0869696969");
    personalInformation.setAddress("Mezdra");
    personalInformation.setCitizenship("Bulgarian");
    personalInformation.setAge(29);
    personalInformation.setMarital_status("single");
    personalInformation.setChildren(0);

    mockMvc
        .perform(put("/svc/personalinformation/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(personalInformation)))
        .andExpect(status().isOk())
        .andDo(print());

    logger.info("Test Update Personal Information passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdatePersonalInformationWithWrongId() throws Exception
  {
    PersonalInformation personalInformation = new PersonalInformation();
    personalInformation.setFirstName("Bai");
    personalInformation.setLastName("Vanio");
    personalInformation.setPhone("0899999999");
    personalInformation.setAddress("Mezdra");
    personalInformation.setCitizenship("Bulgarian");
    personalInformation.setAge(29);
    personalInformation.setMarital_status("married");
    personalInformation.setChildren(2);

    mockMvc
        .perform(put("/svc/personalinformation/{id}", 97)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(personalInformation)))
        .andExpect(status().isBadRequest())
        .andDo(print());

    logger.info("Test Update Personal Information with Wrong Id passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdatePersonalInformationWithoutArgs() throws Exception
  {
    PersonalInformation personalInformation = new PersonalInformation();
    personalInformation.setFirstName("");
    personalInformation.setLastName("");
    personalInformation.setPhone("");
    personalInformation.setAddress("");
    personalInformation.setCitizenship("");
    personalInformation.setAge(18);
    personalInformation.setMarital_status("");
    personalInformation.setChildren(0);

    mockMvc
        .perform(put("/svc/personalinformation/{id}", 8)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(personalInformation)))
        .andExpect(status().isBadRequest())
        .andDo(print());

    logger.info("Test Update Personal Information With No Arguments passed successful");
  }

  //testing delete
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeletePersonalInformation() throws Exception
  {
    mockMvc
        .perform(delete("/svc/personalinformation/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isResetContent());

    logger.info("Test Delete Personal Information passed successful");
  }

  //testing get by id
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetPersonalInformationById() throws Exception
  {
    mockMvc
        .perform(get("/svc/personalinformation/{id}", 2))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id_client").value(2));

    logger.info("Test Get Personal Information By Id passed successful");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetPersonalInformationByWrongId() throws Exception
  {
    mockMvc
        .perform(get("/svc/personalinformation/{id}", 97))
        .andDo(print())
        .andExpect(status().isBadRequest());

    logger.info("AntiTest Get Personal Information By Wrong Id passed successful");
  }

  //testing get all
  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetAllPersonalInformation() throws Exception
  {
    mockMvc
        .perform(get("/svc/personalinformation"))
        .andDo(print())
        .andExpect(status().isIAmATeapot())
        .andReturn();

    logger.info("Test Get All Personal Information passed successful");
  }

}

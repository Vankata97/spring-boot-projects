package codeacademy.loanrequest.controllertests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codeacademy.loanrequest.dto.RegistrationDto;
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
public class RegistrationControllerTest
{

  private static final Logger  logger = LogManager.getLogger(RegistrationControllerTest.class);
  @Autowired
  private              MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Testo");
    registrationDto.setPassword("TestParola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "17f018a7-5bef-4e83-bd45-e4ce5591a912")
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isCreated())
        .andDo(print());
    logger.info("Test create account passed successful!");
  }

  @Test
  public void testForNotMatchingPasswordWhileCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Testo");
    registrationDto.setPassword("TestPaola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "17f018a7-5bef-4e83-bd45-e4ce5591a912")
            .queryParam("id", String.valueOf(3))
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Password don't match")));
    logger.info("No matching password while create account passed successful!");
  }

  @Test
  public void testForDuplicateTokenWhileCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Testovv");
    registrationDto.setPassword("TestParola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "ec53f378-d904-4be6-9abe-ab017dded1df")
            .queryParam("id", String.valueOf(2))
        )
        .andExpect(status().isBadRequest())
        .andExpect(content()
            .string(containsString("The username with this token is already registered")));

    logger.info("Test for duplicated token passed successful!");
  }
  @Test
  public void testNoValidTokenForId() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Testovv");
    registrationDto.setPassword("TestParola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "ec53f378-d904-4be6-9abe-ab017dded1df")
            .queryParam("id", String.valueOf(22))
        )
        .andExpect(status().isBadRequest())
        .andExpect(content()
            .string(containsString("No valid token for this username")));

    logger.info("Test for invalid token for username!");
  }
  @Test
  public void testForInvalidTokenWhileCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Testo");
    registrationDto.setPassword("TestParola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "7b62eb82-4eb3-481f-8-457387a6f32bsdf")
            .queryParam("id", String.valueOf(3))
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Invalid token")));

    logger.info("Test for invalid token while creating account!");
  }
  @Test
  public void testForInvalidTokenLengthWhileCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Testo");
    registrationDto.setPassword("TestParola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "7b62eb82-4eb3-481f-8-457387a6f32bs")
            .queryParam("id", String.valueOf(3))
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Invalid token!")));

    logger.info("Test for invalid token while creating account!");
  }

  @Test
  public void testForDuplicateNameCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("Vladislav");
    registrationDto.setPassword("TestParola1@");
    registrationDto.setPasswordConfirmation("TestParola1@");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "17f018a7-5bef-4e83-bd45-e4ce5591a912")
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("The username already exist")));
    logger.info("Test for duplicated name while creating account passed successful!");
  }


  @Test
  public void testEmptyInputWhileCreateAccount() throws Exception
  {
    RegistrationDto registrationDto = new RegistrationDto();
    registrationDto.setUsername("");
    registrationDto.setPassword("");
    registrationDto.setPasswordConfirmation("");

    mockMvc
        .perform(post("/svc/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationDto))
            .queryParam("token", "17f018a7-5bef-4e83-bd45-e4ce5591a912")
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isBadRequest());
    logger.info("Test for empty input while creating account passed successful!");
  }

  @WithMockUser(username = "Vladislav", roles = "USER")
  @Test
  public void testLogin() throws Exception
  {

    mockMvc
        .perform(post("/svc/login")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    logger.info("Test for login successful!");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void changeRoles() throws Exception
  {
    mockMvc
        .perform(post("/svc/roles")
            .queryParam("id", "2")
            .queryParam("role", "ADMIN"))
        .andExpect(status().isOk())
        .andDo(print());

    logger.info("Test change role successful!");
  }

}

package codeacademy.loanrequest.controllertests;

import static codeacademy.loanrequest.utils.Constants.CONTRACT_TYPE1;
import static codeacademy.loanrequest.utils.Constants.CONTRACT_TYPE2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codeacademy.loanrequest.model.FinancialStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"/QueriesForTest.sql"})
class FinancialStatusControllerTest
{

  private static final Logger  logger = LogManager.getLogger(FinancialStatusControllerTest.class);
  @Autowired
  private              MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createdFinancialStatusTest() throws Exception
  {
    FinancialStatus financialStatus = new FinancialStatus();
    financialStatus.setJobTitle("driver");
    financialStatus.setTypeOfContract(CONTRACT_TYPE1);
    financialStatus.setMonthlySalary(new BigDecimal((3000)));
    financialStatus.setOtherIncomes(BigDecimal.ZERO);
    financialStatus.setOtherLoans(BigDecimal.ZERO);

    mockMvc
        .perform(post("/svc/financialstatus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(financialStatus))
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isCreated())
        .andDo(print());
    logger.info("Test financial status created passed successful!");
  }

  @Test
  public void createdFinancialStatusRejectTest() throws Exception
  {
    FinancialStatus financialStatus = new FinancialStatus();
    financialStatus.setJobTitle("driver");
    financialStatus.setTypeOfContract(CONTRACT_TYPE1);
    financialStatus.setMonthlySalary(new BigDecimal((7000)));
    financialStatus.setOtherIncomes(BigDecimal.ZERO);
    financialStatus.setOtherLoans(BigDecimal.ZERO);

    mockMvc
        .perform(post("/svc/financialstatus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(financialStatus))
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test for rejection of creation of financial status passed successful!");
  }

  @Test
  public void createdFinancialStatusReject2Test() throws Exception
  {
    FinancialStatus financialStatus = new FinancialStatus();
    financialStatus.setJobTitle("driver");
    financialStatus.setTypeOfContract(CONTRACT_TYPE1);
    financialStatus.setMonthlySalary(new BigDecimal((500)));
    financialStatus.setOtherIncomes(BigDecimal.ZERO);
    financialStatus.setOtherLoans(BigDecimal.ZERO);

    mockMvc
        .perform(post("/svc/financialstatus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(financialStatus))
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isCreated())
        .andDo(print());

    logger.info("Test financial status below minimum requirements passed successful");
  }

  @Test
  public void createdFinancialStatusWrongInputDataTest() throws Exception
  {
    FinancialStatus financialStatus = new FinancialStatus();
    financialStatus.setJobTitle("driver");
    financialStatus.setTypeOfContract(CONTRACT_TYPE2);
    financialStatus.setMonthlySalary(new BigDecimal((400)));
    financialStatus.setOtherIncomes(BigDecimal.ZERO);
    financialStatus.setOtherLoans(BigDecimal.ZERO);

    mockMvc
        .perform(post("/svc/financialstatus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(financialStatus))
            .queryParam("id", String.valueOf(199)))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers
            .content()
            .string(Matchers.containsString("Please insert personal information first!")))
        .andDo(print());

    logger.info("Test financial status wrong input data passed successful!");
  }

  @Test
  public void createdFinancialStatusEmptyInputDataTest() throws Exception
  {
    FinancialStatus financialStatus = new FinancialStatus();
    financialStatus.setJobTitle("");
    financialStatus.setTypeOfContract("");
    financialStatus.setMonthlySalary(new BigDecimal((400)));
    financialStatus.setOtherIncomes(BigDecimal.ZERO);
    financialStatus.setOtherLoans(BigDecimal.ZERO);

    mockMvc
        .perform(post("/svc/financialstatus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(financialStatus))
            .queryParam("id", String.valueOf(3)))
        .andExpect(status().isBadRequest())
        .andDo(print());

    logger.info("Test create financial status empty input passed successful!");
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void findAllFinancialStatusTest() throws Exception
  {
    mockMvc
        .perform(get("/svc/financialstatus"))
        .andExpect(status().isOk())
        .andDo(print());

    logger.info("Test find all financial status passed successful");
  }

}

package codeacademy.loanrequest.model;

import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_JOB_TITLE;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_CONTRACT_TYPE;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_JOB_TITLE;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_SALARY;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_CONTRACT_TYPE;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_JOB_TITLE;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialStatus
{

  private int        id_client;
  @Pattern(regexp = VALIDATE_JOB_TITLE, message = INVALID_JOB_TITLE)
  @NotBlank(message = EMPTY_JOB_TITLE)
  private String     jobTitle;
  @Pattern(regexp = VALIDATE_CONTRACT_TYPE, message = INVALID_CONTRACT_TYPE)
  @NotBlank(message = INVALID_CONTRACT_TYPE)
  private String     typeOfContract;
  @DecimalMin(value = "0.0", inclusive = false, message = INVALID_SALARY)
  @Digits(integer = 8, fraction = 2, message = INVALID_SALARY)
  @NotNull(message = INVALID_SALARY)
  private BigDecimal monthlySalary;
  private BigDecimal otherIncomes;
  private BigDecimal otherLoans;


}

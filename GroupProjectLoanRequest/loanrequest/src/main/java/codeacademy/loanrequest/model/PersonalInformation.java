package codeacademy.loanrequest.model;

import static codeacademy.loanrequest.utils.Constants.MAX_VALID_AGE;
import static codeacademy.loanrequest.utils.Constants.MIN_VALID_AGE;
import static codeacademy.loanrequest.utils.ErrorMessages.ABOVE_MAX_AGE;
import static codeacademy.loanrequest.utils.ErrorMessages.BELOW_MIN_AGE;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_AGE;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_CHILDREN;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_FIRST_NAME;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_LAST_NAME;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_MARITAL_STATUS;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_PHONE_NUMBER;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_ADDRESS;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_CITIZENSHIP;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_FIRST_NAME;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_LAST_NAME;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_MARITAL_STATUS;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_PHONE_NUMBER;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_FIRST_NAME;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_LAST_NAME;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_MARITAL_STATUS;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_PHONE_NUMBER;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformation
{

  private int    id_client;
  @Pattern(regexp = VALIDATE_FIRST_NAME, message = INVALID_FIRST_NAME)
  @NotBlank(message = EMPTY_FIRST_NAME)
  private String firstName;
  @Pattern(regexp = VALIDATE_LAST_NAME, message = INVALID_LAST_NAME)
  @NotBlank(message = EMPTY_LAST_NAME)
  private String lastName;
  @Pattern(regexp = VALIDATE_PHONE_NUMBER, message = INVALID_PHONE_NUMBER)
  @NotBlank(message = EMPTY_PHONE_NUMBER)
  private String phone;
  @NotBlank(message = INVALID_ADDRESS)
  private String address;
  @NotBlank(message = INVALID_CITIZENSHIP)
  private String citizenship;
  @Max(value = MAX_VALID_AGE, message = ABOVE_MAX_AGE)
  @Min(value = MIN_VALID_AGE, message = BELOW_MIN_AGE)
  @NotNull(message = EMPTY_AGE)
  private int    age;
  @Pattern(regexp = VALIDATE_MARITAL_STATUS, message = INVALID_MARITAL_STATUS)
  @NotBlank(message = EMPTY_MARITAL_STATUS)
  private String marital_status;
  @NotNull(message = EMPTY_CHILDREN)
  private int    children;

}

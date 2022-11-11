package codeacademy.loanrequest.dto;

import static codeacademy.loanrequest.utils.Constants.MAX_USERNAME_SYMBOLS;
import static codeacademy.loanrequest.utils.Constants.MIN_USERNAME_SYMBOLS;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_PASSWORD;
import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_USERNAME;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_MAX_USERNAME;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_MIN_USERNAME;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_PASSWORD;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_PASSWORD;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto
{

  @Size(min = MIN_USERNAME_SYMBOLS, message = INVALID_MIN_USERNAME)
  @Size(max = MAX_USERNAME_SYMBOLS, message = INVALID_MAX_USERNAME)
  @NotBlank(message = EMPTY_USERNAME)
  private String username;
  @Pattern(regexp = VALIDATE_PASSWORD, message = INVALID_PASSWORD)
  @NotBlank(message = EMPTY_PASSWORD)
  private String password;
  @Pattern(regexp = VALIDATE_PASSWORD, message = INVALID_PASSWORD)
  @NotBlank(message = EMPTY_PASSWORD)
  private String passwordConfirmation;


}

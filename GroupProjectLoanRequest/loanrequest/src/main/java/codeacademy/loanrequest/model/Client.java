package codeacademy.loanrequest.model;

import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_MAIL;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_EMAIL;
import static codeacademy.loanrequest.utils.ErrorMessages.VALIDATE_EMAIL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client
{

  private int    id_client;
  @NotEmpty(message = EMPTY_MAIL)
  @Email(regexp = VALIDATE_EMAIL, message = INVALID_EMAIL)
  private String e_mail_rc;

}

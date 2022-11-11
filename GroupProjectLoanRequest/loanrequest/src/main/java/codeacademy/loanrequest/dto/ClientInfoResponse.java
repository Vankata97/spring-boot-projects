package codeacademy.loanrequest.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfoResponse
{

  private String     e_mail_rc;
  private String     firstName;
  private String     lastName;
  private String     phone;
  private String     address;
  private BigDecimal amount;
  private int        months;

}

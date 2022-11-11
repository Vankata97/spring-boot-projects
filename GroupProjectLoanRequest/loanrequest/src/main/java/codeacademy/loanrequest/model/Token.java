package codeacademy.loanrequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token
{

  private int    token_id;
  private int    client_id;
  private String token;

}

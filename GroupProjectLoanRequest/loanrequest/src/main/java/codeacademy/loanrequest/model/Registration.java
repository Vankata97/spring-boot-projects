package codeacademy.loanrequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration
{

  private int    client_id;
  private String username;
  private String password;
  private Role   role;

  public enum Role
  {
    USER,
    ADMIN
  }

}

package codeacademy.loanrequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File
{

  private int    id;
  private String name;
  private String type;
  private byte[] data;

}

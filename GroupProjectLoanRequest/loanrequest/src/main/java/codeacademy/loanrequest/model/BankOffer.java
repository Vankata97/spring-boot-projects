package codeacademy.loanrequest.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankOffer
{

  private int        id_client;
  private BigDecimal minAmount;
  private BigDecimal maxAmount;
  private int        minPeriod;
  private int        maxPeriod;
  private double     interest;


}

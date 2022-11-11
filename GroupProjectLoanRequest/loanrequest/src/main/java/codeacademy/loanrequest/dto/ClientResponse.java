package codeacademy.loanrequest.dto;


import static codeacademy.loanrequest.utils.ErrorMessages.EMPTY_DURATION;
import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_AMOUNT;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse
{

  private int        id_client;
  @DecimalMin(value = "0.0", inclusive = false, message = INVALID_AMOUNT)
  @Digits(integer = 8, fraction = 2, message = INVALID_AMOUNT)
  @NotNull(message = INVALID_AMOUNT)
  private BigDecimal amount;

  @NotNull(message = EMPTY_DURATION)
  private int months;


}


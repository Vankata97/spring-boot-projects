package codeacademy.loanrequest.utils;

import java.math.BigDecimal;


public final class Constants
{

  public static final BigDecimal MIN_AMOUNT_LOAN               = new BigDecimal(10000);
  public static final BigDecimal SECOND_AMOUNT_LOAN            = new BigDecimal(20000);
  public static final BigDecimal THIRD_AMOUNT_LOAN             = new BigDecimal(30000);
  public static final BigDecimal FOURTH_AMOUNT_LOAN            = new BigDecimal(40000);
  public static final BigDecimal MAX_AMOUNT_LOAN               = new BigDecimal(50000);
  public static final int        MIN_MONTHS_FOR_LOAN           = 18;
  public static final int        SECOND_GROUP_MONTHS_FOR_LOAN  = 24;
  public static final int        THIRD_GROUP_MONTHS_FOR_LOAN   = 30;
  public static final int        FOURTH_GROUP_MONTHS_FOR_LOAN  = 36;
  public static final int        FIFTH_GROUP_MONTHS_FOR_LOAN   = 40;
  public static final int        SIXTH_GROUP_MONTHS_FOR_LOAN   = 48;
  public static final int        SEVENTH_GROUP_MONTHS_FOR_LOAN = 60;
  public static final int        EIGHT_GROUP_MONTHS_FOR_LOAN   = 72;
  public static final int        NINTH_GROUP_MONTHS_FOR_LOAN   = 96;
  public static final int        MAX_MONTHS_FOR_LOAN           = 120;
  public static final Double     MIN_INTEREST                  = 3.5;
  public static final Double     MID_INTEREST                  = 4.5;
  public static final Double     HIGH_INTEREST                 = 5.5;
  public static final Double     MAX_INTEREST                  = 6.5;
  public static final BigDecimal GROUP_ONE_MIN_SUM_REQUIRED    = new BigDecimal(500);
  public static final BigDecimal GROUP_TWO_MIN_SUM_REQUIRED    = new BigDecimal(800);
  public static final BigDecimal GROUP_THREE_MIN_SUM_REQUIRED  = new BigDecimal(1000);
  public static final BigDecimal GROUP_THREE_MAX_SUM_REQUIRED  = new BigDecimal(1500);
  public static final String     CONTRACT_TYPE1                = "Permanent";
  public static final String     CONTRACT_TYPE2                = "Temporary";
  public static final int        MIN_AGE_FOR_LOAN              = 18;
  public static final int        GROUP_ONE_MAX_AGE             = 24;
  public static final int        GROUP_TWO_MIN_AGE             = 25;
  public static final int        GROUP_TWO_MAX_AGE             = 35;
  public static final int        GROUP_THREE_MIN_AGE           = 36;
  public static final int        GROUP_THREE_MAX_AGE           = 45;
  public static final int        GROUP_FOUR_MIN_AGE            = 46;
  public static final int        GROUP_FOUR_MAX_AGE            = 55;
  public static final int        GROUP_FIVE_MIN_AGE            = 56;
  public static final int        MAX_AGE_FOR_LOAN              = 65;

  public static final int MIN_VALID_AGE        = 18;
  public static final int MAX_VALID_AGE        = 120;
  public static final int MIN_USERNAME_SYMBOLS = 3;
  public static final int MAX_USERNAME_SYMBOLS = 15;

  private Constants()
  {
  }
}

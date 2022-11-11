package codeacademy.loanrequest.utils;

public final class ErrorMessages
{

  public static final String EMPTY_MAIL              = "Email cannot be empty!";
  public static final String INVALID_EMAIL           = "Email is not valid !";
  public static final String EMPTY_JOB_TITLE         = "Please enter job title!";
  public static final String VALIDATE_EMAIL          =
      "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\"
          + ".[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
  public static final String VALIDATE_JOB_TITLE      =
      "^[^- '](?=(?![A-Z]?[A-Z]))(?=(?![a-z]+[A-Z]))(?=(?!.*[A-Z][A-Z]))(?=(?!.*[- '][- '.]))(?=(?!.*["
          + ".][-'.]))[A-Za-z- '.]{2,}$";
  public static final String INVALID_JOB_TITLE       = "Please enter a valid job title!";
  public static final String INVALID_CONTRACT_TYPE   = "Please select permanent or temporary!";
  public static final String VALIDATE_CONTRACT_TYPE  = "^(?i)(?:permanent|temporary)$";
  public static final String INVALID_SALARY          = "Please enter a valid salary!";
  public static final String INVALID_FIRST_NAME      = "Please enter a valid name!";
  public static final String EMPTY_FIRST_NAME        = "Please enter first name!";
  public static final String VALIDATE_FIRST_NAME     =
      "^[^- '](?=(?![A-Z]?[A-Z]))(?=(?![a-z]+[A-Z]))(?=(?!.*[A-Z][A-Z]))"
          + "(?=(?!.*[- '][- '.]))(?=(?!.*[.][-'.]))[A-Za-z- '.]{2,}$";
  public static final String INVALID_LAST_NAME       = "Please enter a valid last name!";
  public static final String EMPTY_LAST_NAME         = "Please enter last name!";
  public static final String VALIDATE_LAST_NAME      =
      "^[^- '](?=(?![A-Z]?[A-Z]))(?=(?![a-z]+[A-Z]))(?=(?!.*[A-Z][A-Z]))"
          + "(?=(?!.*[- '][- '.]))(?=(?!.*[.][-'.]))[A-Za-z- '.]{2,}$";
  public static final String VALIDATE_PHONE_NUMBER   = "^\\d{10}$";
  public static final String INVALID_PHONE_NUMBER    = "Please enter a valid phone number!";
  public static final String EMPTY_PHONE_NUMBER      = "Please enter a number!";
  public static final String INVALID_ADDRESS         = "Please enter address!";
  public static final String INVALID_CITIZENSHIP     = "Please enter citizenship!";
  public static final String ABOVE_MAX_AGE           = "Woah, don't lie!";
  public static final String BELOW_MIN_AGE           = "18 needed to request loan!";
  public static final String EMPTY_AGE               = "Please enter your age!";
  public static final String VALIDATE_MARITAL_STATUS = "^(?i)(?:Married|Single)$";
  public static final String INVALID_MARITAL_STATUS  = "Please select married or single!";
  public static final String EMPTY_MARITAL_STATUS    = "Please select marital status!";
  public static final String EMPTY_CHILDREN          = "Please select marital status!";
  public static final String INVALID_MIN_USERNAME    = "Username should be min 3 symbols!";
  public static final String INVALID_MAX_USERNAME    = "Username should b max 15 symbols!";
  public static final String EMPTY_USERNAME          = "Please enter a username!";
  public static final String VALIDATE_PASSWORD       = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)"
      + ".{8,20}$";
  public static final String INVALID_PASSWORD        = "The password "
      + "should contain at least one digit, lower case, upper case and special character!";
  public static final String EMPTY_PASSWORD          = "Please enter a password!";
  public static final String INVALID_AMOUNT          = "Please enter a valid amount!";

  public static final String EMPTY_DURATION = "Please enter desired duration!";
  public static final String INVALID_TOKEN="Invalid token!";

  private ErrorMessages()
  {
  }
}

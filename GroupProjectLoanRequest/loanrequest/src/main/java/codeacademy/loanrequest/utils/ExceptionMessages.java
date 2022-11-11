package codeacademy.loanrequest.utils;

public final class ExceptionMessages
{

  public static final String BLANK_BANK_OFFER           = "There is no such offer!";
  public static final String WRONG_E_MAIL               = "This email is already registered!\n"
      + "Please make sure you are entering your email correctly.";
  public static final String USER_NOT_FOUND             = "No such username was found!";
  public static final String ERROR_SENDING_E_MAIL       = "Error while sending email!";
  public static final String USER_DOES_NOT_EXIST        = "The user does not exist!";
  public static final String E_MAIL_SENT                = "Mail Sent Successfully...";
  public static final String FILE_TOO_LARGE             = "File too large";
  public static final String PDF_UPLOAD                 = "Please upload pdf";
  public static final String INSERT_PERSONAL_INFO_FIRST = "Please insert personal information first!";
  public static final String INVALID_USERNAME           = "No valid token for this username";
  public static final String ALREADY_USED_TOKEN         = "The username with this token is already registered";
  public static final String ALREADY_USED_USERNAME      = "The username already exist";
  public static final String WRONG_PASSWORD             = "Password don't match";
  public static final String INVALID_TOKEN              = "Invalid token";

  public static final String SUCCESSFUL_LOGIN = "Login Successful";

  private ExceptionMessages()
  {
  }
}

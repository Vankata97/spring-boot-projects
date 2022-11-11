package codeacademy.loanrequest.utils;

public final class EmailTemplates
{

  public static String EMAIL_TITLE_APPLICATION = "Apply for loan request now!";

  public static String EMAIL_APPLICATION_CONTENT = "To apply for loan, please click on the following link: "
      + "http://localhost:8082/svc/personalinformation?id=";
  public static String EMAIL_TITLE_PROCESS       = "Loan request application !";
  public static String REGISTRATION_TOKEN        = "\n\n \n" + "To "
      + "continue "
      + "with "
      + "your registration please "
      + "click the following link:http://localhost:8082/svc/registration?token=";
  public static String EMAIL_REJECTION_CONTENT   = "We are so sorry to inform that your loan request has been "
      + "rejected.. !";

  private EmailTemplates()
  {
  }
}

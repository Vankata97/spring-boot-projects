package codeacademy.loanrequest.service;

import static codeacademy.loanrequest.utils.EmailTemplates.EMAIL_APPLICATION_CONTENT;
import static codeacademy.loanrequest.utils.EmailTemplates.EMAIL_REJECTION_CONTENT;
import static codeacademy.loanrequest.utils.EmailTemplates.EMAIL_TITLE_APPLICATION;
import static codeacademy.loanrequest.utils.EmailTemplates.EMAIL_TITLE_PROCESS;
import static codeacademy.loanrequest.utils.EmailTemplates.REGISTRATION_TOKEN;
import static codeacademy.loanrequest.utils.ExceptionMessages.ERROR_SENDING_E_MAIL;
import static codeacademy.loanrequest.utils.ExceptionMessages.E_MAIL_SENT;
import static codeacademy.loanrequest.utils.ExceptionMessages.USER_DOES_NOT_EXIST;

import codeacademy.loanrequest.dao.client.ClientDaoImpl;
import codeacademy.loanrequest.dao.token.TokenDao;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.Client;
import codeacademy.loanrequest.model.Token;
import java.math.BigDecimal;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;


@Repository
public class EmailService
{

  private final JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  private final ClientDaoImpl clientDaoImpl;
  private final TokenDao      tokenDao;

  public EmailService(JavaMailSender javaMailSender, ClientDaoImpl clientDaoImpl, TokenDao tokenDao)
  {
    this.javaMailSender = javaMailSender;
    this.clientDaoImpl = clientDaoImpl;
    this.tokenDao = tokenDao;
  }

  public String sendMail(int id)
  {
    try {
      Client client = clientDaoImpl.findById(id).get();

      SimpleMailMessage mailMessage = new SimpleMailMessage();

      mailMessage.setFrom(sender);
      mailMessage.setTo(client.getE_mail_rc());
      mailMessage.setText(EMAIL_APPLICATION_CONTENT + client.getId_client());
      mailMessage.setSubject(EMAIL_TITLE_APPLICATION);
      javaMailSender.send(mailMessage);
      return E_MAIL_SENT;
    }
    catch (MailSendException mse) {
      throw new TeamOneException(ERROR_SENDING_E_MAIL);
    }
    catch (EmptyResultDataAccessException erdae) {
      throw new TeamOneException(USER_DOES_NOT_EXIST);
    }
  }

  public void acceptMail(int id, BigDecimal amount, BigDecimal paymentNoInterest, BigDecimal paymentWithInterest,
      BigDecimal totalAmountWithInterest, double interest, int months)
  {
    try {

      Client client = clientDaoImpl.findById(id).get();
      Token token = new Token();
      token.setClient_id(id);
      token.setToken(UUID
          .randomUUID()
          .toString());
      tokenDao.save(token);

      StringBuilder st = new StringBuilder();
      st.append("<html>" +
          "<head>" +
          "<style>" +
          " th{"
          + " border:1px solid black;"
          + " background-color:black;"
          + " color:white;"
          + "} td {"
          + "  border:2px solid black;"
          + "}"
          + "table{"
          + " border:4px solid black;"
          + "}" +
          "</style>" +
          "</head>" +
          "<body>" +
          "<h2>Your request has been approved! This is your payment plan:</h2>" +
          "<br>" +
          "<table>" +
          "<tr>" +
          "<th>Month</th>" +
          "<th>Payment</th>" +
          "<th>Interest</th>" +
          "<th>Payment With interest</th>" +
          "<th>Amount</th>" +
          "<th>Amount With interest</th>" +
          "<tr>" +
          "</tr>" +
          "<tr>");

      for (int i = 1; i <= months; i++) {
        st
            .append("<td>")
            .append(i)
            .append("</td>")
            .append("<td>")
            .append(paymentNoInterest)
            .append("</td>")
            .append("<td>")
            .append(interest)
            .append("</td>")
            .append("<td>")
            .append(paymentWithInterest)
            .append("</td>")
            .append("<td>")
            .append(amount)
            .append("</td>")
            .append("<td>")
            .append(totalAmountWithInterest)
            .append("</td>")
            .append("<tr>");

        amount = amount.subtract(paymentNoInterest);
        totalAmountWithInterest = totalAmountWithInterest.subtract(paymentWithInterest);
      }
      st.append("</tr>" +
          "</table>" +
          "</body>" +
          "</html>");

      MimeMessage message = javaMailSender.createMimeMessage();

      message.setSubject(EMAIL_TITLE_PROCESS);
      MimeMessageHelper helper;
      helper = new MimeMessageHelper(message, true);
      helper.setFrom(sender);
      helper.setTo(client.getE_mail_rc());
      helper.setText(st + REGISTRATION_TOKEN + token.getToken() + "&id=" + id, true);

      javaMailSender.send(message);

    }
    catch (MessagingException e) {
      throw new TeamOneException(ERROR_SENDING_E_MAIL);
    }
    catch (EmptyResultDataAccessException erdae) {
      throw new TeamOneException(USER_DOES_NOT_EXIST);
    }
  }

  public void rejectMail(int id)
  {
    try {
      Client client = clientDaoImpl.findById(id).get();

      SimpleMailMessage mailMessage = new SimpleMailMessage();

      mailMessage.setFrom(sender);
      mailMessage.setTo(client.getE_mail_rc());
      mailMessage.setText(EMAIL_REJECTION_CONTENT);
      mailMessage.setSubject(EMAIL_TITLE_PROCESS);
      javaMailSender.send(mailMessage);
    }
    catch (MailSendException mse) {
      throw new TeamOneException(ERROR_SENDING_E_MAIL);
    }
    catch (EmptyResultDataAccessException erdae) {
      throw new TeamOneException(USER_DOES_NOT_EXIST);
    }
  }
}

package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.service.EmailService;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class EmailController
{

  private final EmailService emailService;

  public EmailController(EmailService emailService)
  {
    this.emailService = emailService;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/sendMail")

  public String sendMail(@Valid @RequestParam int id)
  {
    return emailService.sendMail(id);
  }

}
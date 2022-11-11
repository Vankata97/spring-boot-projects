package codeacademy.loanrequest.controller;

import static codeacademy.loanrequest.utils.ErrorMessages.INVALID_TOKEN;
import static codeacademy.loanrequest.utils.ExceptionMessages.SUCCESSFUL_LOGIN;

import codeacademy.loanrequest.dto.RegistrationDto;
import codeacademy.loanrequest.service.RegistrationService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/svc")
public class RegistrationController
{

  private final RegistrationService registrationService;


  public RegistrationController(RegistrationService registrationService)
  {
    this.registrationService = registrationService;
  }

  @PostMapping("/registration")
  @ResponseStatus(HttpStatus.CREATED)
  public void createAccount(@RequestBody @Valid RegistrationDto registrationDto,
      @RequestParam @NotEmpty @Size(min = 36, max = 36, message = INVALID_TOKEN) String token,
      @RequestParam("id") int id)
  {
    registrationService.createRegistration(registrationDto, id, token);
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public String login()
  {
    return SUCCESSFUL_LOGIN;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/roles")
  @ResponseStatus(HttpStatus.OK)
  public void roles(@RequestParam int id, @RequestParam String role)
  {
    registrationService.updateRole(id, role);
  }

}

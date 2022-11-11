package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.dto.ClientInfoResponse;
import codeacademy.loanrequest.service.ClientInfoResponseService;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class ClientInfoController
{

  private final ClientInfoResponseService clientInfoResponseService;

  public ClientInfoController(ClientInfoResponseService clientInfoResponseService)
  {
    this.clientInfoResponseService = clientInfoResponseService;
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/clientinfo")
  @ResponseStatus(HttpStatus.OK)
  public ClientInfoResponse findByEmail(@RequestParam int id, Principal principal)
  {
    return clientInfoResponseService.findClientData(id, principal);
  }
}

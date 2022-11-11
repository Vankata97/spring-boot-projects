package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.dto.ClientResponse;
import codeacademy.loanrequest.service.ClientResponseService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class ClientResponseController
{

  private final ClientResponseService clientResponseService;

  public ClientResponseController(ClientResponseService clientResponseService)
  {
    this.clientResponseService = clientResponseService;
  }

  @PostMapping("/response")
  @ResponseStatus(HttpStatus.CREATED)
  public void createClientResponse(@Valid @RequestBody ClientResponse clientResponse, @RequestParam int id)
  {
    clientResponseService.save(clientResponse, id);
  }
}

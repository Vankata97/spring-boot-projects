package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.model.Client;
import codeacademy.loanrequest.service.ClientService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class ClientController
{

  private final ClientService clientService;

  public ClientController(ClientService clientService)
  {
    this.clientService = clientService;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/client")
  @ResponseStatus(HttpStatus.CREATED)
  public void createClient(@Valid @RequestBody Client client)
  {
    clientService.createClient(client);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/client/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateClient(@Valid @RequestBody Client client, @PathVariable int id)
  {
    clientService.updateClient(client, id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/client/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteClient(@PathVariable int id)
  {
    clientService.deleteClient(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/client/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Client getClientById(@PathVariable int id)
  {
    return clientService.getById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/client")
  @ResponseStatus(HttpStatus.OK)
  public List<Client> getAllClients()
  {
    return clientService.getAllClients();
  }

}

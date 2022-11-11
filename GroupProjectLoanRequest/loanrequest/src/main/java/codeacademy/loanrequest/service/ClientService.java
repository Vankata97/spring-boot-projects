package codeacademy.loanrequest.service;

import static codeacademy.loanrequest.utils.ExceptionMessages.USER_DOES_NOT_EXIST;
import static codeacademy.loanrequest.utils.ExceptionMessages.WRONG_E_MAIL;

import codeacademy.loanrequest.dao.client.ClientDaoImpl;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.Client;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClientService
{

  private final ClientDaoImpl clientDaoImpl;

  public ClientService(ClientDaoImpl clientDaoImpl)
  {
    this.clientDaoImpl = clientDaoImpl;
  }

  public void createClient(Client client)
  {
    if (!clientDaoImpl
        .findByEmail(client.getE_mail_rc())
        .isPresent()) {
      clientDaoImpl.save(client);
    }
    else {
      throw new TeamOneException(WRONG_E_MAIL);
    }
  }

  public void updateClient(Client client, int id)
  {
    if (clientDaoImpl
        .findById(id)
        .isPresent()) {
      Client client1 = clientDaoImpl
          .findById(id)
          .get();
      client1.setId_client(id);
      client1.setE_mail_rc(client.getE_mail_rc());
      if (!clientDaoImpl
          .findByEmail(client.getE_mail_rc())
          .isPresent()) {
        clientDaoImpl.update(client1);
      }
      else {
        throw new TeamOneException(WRONG_E_MAIL);
      }
    }
    else {
      throw new TeamOneException(USER_DOES_NOT_EXIST);
    }
  }

  public void deleteClient(int id)
  {
    clientDaoImpl.delete(id);
  }

  public Client getById(int id)
  {
    return clientDaoImpl
        .findById(id)
        .orElseThrow(() -> new TeamOneException(USER_DOES_NOT_EXIST));
  }

  public List<Client> getAllClients()
  {
    return clientDaoImpl.findAll();
  }


}

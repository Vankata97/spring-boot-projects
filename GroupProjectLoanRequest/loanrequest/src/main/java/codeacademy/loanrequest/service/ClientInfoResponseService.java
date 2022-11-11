package codeacademy.loanrequest.service;

import codeacademy.loanrequest.dao.registered.client.data.ClientInfoResponseDaoImpl;
import codeacademy.loanrequest.dao.registration.RegistrationDao;
import codeacademy.loanrequest.dto.ClientInfoResponse;
import codeacademy.loanrequest.exceptions.TeamOneException;
import java.security.Principal;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@Service
public class ClientInfoResponseService
{
private final RegistrationDao registrationDao;
  private final ClientInfoResponseDaoImpl registeredClientDataDao;

  public ClientInfoResponseService(RegistrationDao registrationDao, ClientInfoResponseDaoImpl registeredClientDataDao)
  {
    this.registrationDao = registrationDao;
    this.registeredClientDataDao = registeredClientDataDao;
  }


  public ClientInfoResponse findClientData(int id, Principal principal)
  {
    if (registrationDao.getByName(principal.getName()).get().getClient_id()==id){
      return registeredClientDataDao.findById(id);
    }
    else throw new TeamOneException("Unauthorized");
  }
}

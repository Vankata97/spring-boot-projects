package codeacademy.loanrequest.service;

import static codeacademy.loanrequest.utils.ExceptionMessages.ALREADY_USED_TOKEN;
import static codeacademy.loanrequest.utils.ExceptionMessages.ALREADY_USED_USERNAME;
import static codeacademy.loanrequest.utils.ExceptionMessages.INVALID_TOKEN;
import static codeacademy.loanrequest.utils.ExceptionMessages.INVALID_USERNAME;
import static codeacademy.loanrequest.utils.ExceptionMessages.WRONG_PASSWORD;

import codeacademy.loanrequest.dao.registration.RegistrationDao;
import codeacademy.loanrequest.dao.token.TokenDao;
import codeacademy.loanrequest.dto.RegistrationDto;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.Registration;
import codeacademy.loanrequest.model.Registration.Role;
import codeacademy.loanrequest.model.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService
{

  private final RegistrationDao registrationDao;
  private final PasswordEncoder passwordEncoder;
  private final TokenDao        tokenDao;


  public RegistrationService(RegistrationDao registrationDao, PasswordEncoder passwordEncoder, TokenDao tokenDao)
  {
    this.registrationDao = registrationDao;
    this.passwordEncoder = passwordEncoder;
    this.tokenDao = tokenDao;
  }

  public void createRegistration(RegistrationDto registrationDto, int id, String token)
  {
    if (!tokenDao
        .getById(id)
        .isPresent()) {
      throw new TeamOneException(INVALID_USERNAME);
    }
    else {
      Token token1 = tokenDao
          .getById(id)
          .get();

      if (registrationDao
          .getById(id)
          .isPresent()) {
        throw new TeamOneException(ALREADY_USED_TOKEN);
      }
      if (token1
          .getToken()
          .equals(token)) {

        if (registrationDao
            .getByName(registrationDto.getUsername())
            .isPresent()) {
          throw new TeamOneException(ALREADY_USED_USERNAME);
        }
        else if (!(registrationDto
            .getPassword()
            .equals(registrationDto.getPasswordConfirmation()))) {
          throw new TeamOneException(WRONG_PASSWORD);
        }
        else {
          Registration registration = new Registration();
          registration.setClient_id(id);
          registration.setUsername(registrationDto.getUsername());
          registration.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
          registrationDao.save(registration);
        }
      }
      else {
        throw new TeamOneException(INVALID_TOKEN);
      }
    }
  }

  public void updateRole(int id, String role)
  {
    Registration registration = new Registration();
    registration.setClient_id(id);
    registration.setRole(Role.valueOf(role));
    registrationDao.update(registration);
  }

}

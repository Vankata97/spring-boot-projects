package codeacademy.loanrequest.service;


import static codeacademy.loanrequest.utils.ExceptionMessages.USER_NOT_FOUND;

import codeacademy.loanrequest.dao.registration.RegistrationDaoImpl;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.Registration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceDetailsImpl implements UserDetailsService
{

  private final RegistrationDaoImpl registrationDao;

  public ClientServiceDetailsImpl(RegistrationDaoImpl registration)
  {
    this.registrationDao = registration;
  }


  @Override
  public UserDetails loadUserByUsername(String username)
  {

    Registration clientRegister = registrationDao
        .getByName(username)
        .orElseThrow(() -> new TeamOneException(USER_NOT_FOUND));

    UserBuilder userBuilder = User.builder();

    return userBuilder
        .username(clientRegister.getUsername())
        .password(clientRegister.getPassword())
        .roles(clientRegister
            .getRole()
            .name())
        .build();
  }
}


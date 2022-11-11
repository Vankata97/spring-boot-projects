package codeacademy.loanrequest.dao.registration;

import codeacademy.loanrequest.model.Registration;
import java.util.Optional;

public interface RegistrationDao
{

  void save(Registration registration);

  Optional<Registration> getById(int id);

  Optional<Registration> getByName(String name);

  void update(Registration registration);
}

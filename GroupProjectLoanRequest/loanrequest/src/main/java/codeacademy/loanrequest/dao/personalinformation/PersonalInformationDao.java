package codeacademy.loanrequest.dao.personalinformation;

import codeacademy.loanrequest.model.PersonalInformation;
import java.util.List;
import java.util.Optional;


public interface PersonalInformationDao
{

  void save(PersonalInformation personalInformation);

  void update(PersonalInformation personalInformation);

  void delete(int id);

  Optional<PersonalInformation> findById(int id);

  List<PersonalInformation> findAll();
}

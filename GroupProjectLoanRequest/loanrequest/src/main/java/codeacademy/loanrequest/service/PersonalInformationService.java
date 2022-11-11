package codeacademy.loanrequest.service;

import static codeacademy.loanrequest.utils.ExceptionMessages.USER_DOES_NOT_EXIST;

import codeacademy.loanrequest.dao.personalinformation.PersonalInformationDaoImpl;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.PersonalInformation;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonalInformationService
{

  private final PersonalInformationDaoImpl personalInformationDaoImpl;

  public PersonalInformationService(
      PersonalInformationDaoImpl personalInformationDaoImpl)
  {
    this.personalInformationDaoImpl = personalInformationDaoImpl;
  }

  public void createPersonalInformation(PersonalInformation personalInformation, int id)
  {
    personalInformation.setId_client(id);
    personalInformationDaoImpl.save(personalInformation);
  }

  public void updatePersonalInformation(PersonalInformation personalInformation, int id)
  {
    if (personalInformationDaoImpl
        .findById(id)
        .isPresent()) {
      PersonalInformation personalInformation1 = personalInformationDaoImpl
          .findById(id)
          .get();
      personalInformation1.setId_client(id);
      personalInformation1.setFirstName(personalInformation.getFirstName());
      personalInformation1.setLastName(personalInformation.getLastName());
      personalInformation1.setPhone(personalInformation.getPhone());
      personalInformation1.setAddress(personalInformation.getAddress());
      personalInformation1.setCitizenship(personalInformation.getCitizenship());
      personalInformation1.setAge(personalInformation.getAge());
      personalInformation1.setMarital_status(personalInformation.getMarital_status());
      personalInformation1.setChildren(personalInformation.getChildren());
      personalInformationDaoImpl.update(personalInformation1);
    }
    else {
      throw new TeamOneException(USER_DOES_NOT_EXIST);
    }
  }

  public void deletePersonalInformation(int id)
  {
    personalInformationDaoImpl.delete(id);
  }

  public PersonalInformation getById(int id)
  {
    PersonalInformation personalInformation = personalInformationDaoImpl
        .findById(id)
        .orElseThrow(() -> new TeamOneException(USER_DOES_NOT_EXIST));
    return personalInformation;
  }

  public List<PersonalInformation> getAllPersonalInformation()
  {
    return personalInformationDaoImpl.findAll();
  }

}

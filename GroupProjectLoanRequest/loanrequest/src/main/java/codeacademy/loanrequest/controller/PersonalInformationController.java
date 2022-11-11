package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.model.PersonalInformation;
import codeacademy.loanrequest.service.PersonalInformationService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class PersonalInformationController
{

  private final PersonalInformationService personalInformationService;

  public PersonalInformationController(
      PersonalInformationService personalInformationService)
  {
    this.personalInformationService = personalInformationService;
  }

  @PostMapping("/personalinformation")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPersonalInformation(@Valid @RequestBody PersonalInformation personalInformation,
      @RequestParam int id)
  {
    personalInformationService.createPersonalInformation(personalInformation, id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/personalinformation/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updatePersonalInformation(@Valid @RequestBody PersonalInformation personalInformation,
      @PathVariable int id)
  {
    personalInformationService.updatePersonalInformation(personalInformation, id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/personalinformation/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deletePersonalInformation(@PathVariable int id)
  {
    personalInformationService.deletePersonalInformation(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/personalinformation/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PersonalInformation getPersonalInformationById(@PathVariable int id)
  {
    return personalInformationService.getById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/personalinformation")
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public List<PersonalInformation> getAllPersonalInformation()
  {
    return personalInformationService.getAllPersonalInformation();
  }
}

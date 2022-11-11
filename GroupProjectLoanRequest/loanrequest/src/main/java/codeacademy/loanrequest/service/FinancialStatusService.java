package codeacademy.loanrequest.service;

import static codeacademy.loanrequest.utils.ExceptionMessages.INSERT_PERSONAL_INFO_FIRST;

import codeacademy.loanrequest.dao.bank.offer.BankOfferDao;
import codeacademy.loanrequest.dao.financialstatus.FinancialStatusDao;
import codeacademy.loanrequest.dao.personalinformation.PersonalInformationDao;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.BankOffer;
import codeacademy.loanrequest.model.FinancialStatus;
import codeacademy.loanrequest.model.PersonalInformation;
import codeacademy.loanrequest.validation.BankOfferValidation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FinancialStatusService
{

  private final EmailService        emailService;
  private final BankOfferValidation validation;
  private final FinancialStatusDao  financialStatusDao;
  private final BankOfferDao           bankOfferDao;
  private final PersonalInformationDao personalInformationDao;

  public FinancialStatusService(EmailService emailService, BankOfferValidation validation,
      FinancialStatusDao financialStatusDao,
      BankOfferDao bankOfferDao, PersonalInformationDao personalInformationDao)
  {
    this.emailService = emailService;
    this.validation = validation;
    this.financialStatusDao = financialStatusDao;
    this.bankOfferDao = bankOfferDao;
    this.personalInformationDao = personalInformationDao;
  }

  public void createFinancialStatus(FinancialStatus financialStatus, int id)
  {

    if (!personalInformationDao
        .findById(id)
        .isPresent()) {
      throw new TeamOneException(INSERT_PERSONAL_INFO_FIRST);
    }
    else {
      PersonalInformation personalInformation1 = personalInformationDao
          .findById(id)
          .get();
      int familyMembers;

      if (personalInformation1
          .getMarital_status()
          .equalsIgnoreCase("married")) {
        familyMembers = 2 + personalInformation1.getChildren();
      }
      else {
        familyMembers = 1 + personalInformation1.getChildren();
      }
      BigDecimal income;

      income = (((financialStatus
          .getMonthlySalary()
          .add(financialStatus.getOtherIncomes()))
          .subtract(financialStatus.getOtherLoans()))
          .divide(new BigDecimal(familyMembers), 2, RoundingMode.HALF_UP));

      if (validation
          .calculateAmount(income)
          .isPresent()) {
        BigDecimal amount = validation
            .calculateAmount(income)
            .get();
        if (validation
            .makeDurationOffer(amount, personalInformation1, financialStatus)
            .isPresent()) {
          financialStatus.setId_client(id);
          BankOffer bankOffer = validation
              .makeDurationOffer(amount, personalInformation1, financialStatus)
              .get();
          financialStatusDao.save(financialStatus);
          bankOfferDao.save(bankOffer);
        }
        else {
          emailService.rejectMail(id);
        }
      }
      else {
        emailService.rejectMail(id);
      }
    }
}

  public List<FinancialStatus> findAllFinancialStatus()
  {
    return financialStatusDao.getAllFinancialStatus();
  }

}

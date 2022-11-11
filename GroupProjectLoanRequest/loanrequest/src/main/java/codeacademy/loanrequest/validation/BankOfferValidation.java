package codeacademy.loanrequest.validation;


import static codeacademy.loanrequest.utils.Constants.CONTRACT_TYPE1;
import static codeacademy.loanrequest.utils.Constants.EIGHT_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.FIFTH_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.FOURTH_AMOUNT_LOAN;
import static codeacademy.loanrequest.utils.Constants.FOURTH_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.GROUP_FIVE_MIN_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_FOUR_MAX_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_FOUR_MIN_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_ONE_MAX_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_ONE_MIN_SUM_REQUIRED;
import static codeacademy.loanrequest.utils.Constants.GROUP_THREE_MAX_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_THREE_MAX_SUM_REQUIRED;
import static codeacademy.loanrequest.utils.Constants.GROUP_THREE_MIN_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_THREE_MIN_SUM_REQUIRED;
import static codeacademy.loanrequest.utils.Constants.GROUP_TWO_MAX_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_TWO_MIN_AGE;
import static codeacademy.loanrequest.utils.Constants.GROUP_TWO_MIN_SUM_REQUIRED;
import static codeacademy.loanrequest.utils.Constants.HIGH_INTEREST;
import static codeacademy.loanrequest.utils.Constants.MAX_AGE_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.MAX_AMOUNT_LOAN;
import static codeacademy.loanrequest.utils.Constants.MAX_INTEREST;
import static codeacademy.loanrequest.utils.Constants.MAX_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.MID_INTEREST;
import static codeacademy.loanrequest.utils.Constants.MIN_AGE_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.MIN_AMOUNT_LOAN;
import static codeacademy.loanrequest.utils.Constants.MIN_INTEREST;
import static codeacademy.loanrequest.utils.Constants.MIN_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.NINTH_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.SECOND_AMOUNT_LOAN;
import static codeacademy.loanrequest.utils.Constants.SECOND_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.SEVENTH_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.SIXTH_GROUP_MONTHS_FOR_LOAN;
import static codeacademy.loanrequest.utils.Constants.THIRD_AMOUNT_LOAN;
import static codeacademy.loanrequest.utils.Constants.THIRD_GROUP_MONTHS_FOR_LOAN;

import codeacademy.loanrequest.dao.bank.offer.BankOfferDao;
import codeacademy.loanrequest.model.BankOffer;
import codeacademy.loanrequest.model.FinancialStatus;
import codeacademy.loanrequest.model.PersonalInformation;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Component;


@Component
public class BankOfferValidation
{

  public final BankOfferDao bankOfferDao;


  public BankOfferValidation(BankOfferDao bankOfferDao)
  {
    this.bankOfferDao = bankOfferDao;
  }

  public Optional<BigDecimal> calculateAmount(BigDecimal income)
  {
    if (income.compareTo(GROUP_ONE_MIN_SUM_REQUIRED) > 0 && income.compareTo(GROUP_TWO_MIN_SUM_REQUIRED) < 0) {
      return Optional.of(SECOND_AMOUNT_LOAN);
    }
    else if (income.compareTo(GROUP_TWO_MIN_SUM_REQUIRED) > 0 && income.compareTo(GROUP_THREE_MIN_SUM_REQUIRED) < 0) {
      return Optional.of(THIRD_AMOUNT_LOAN);
    }
    else if (income.compareTo(GROUP_THREE_MIN_SUM_REQUIRED) > 0 && income.compareTo(GROUP_THREE_MAX_SUM_REQUIRED) < 0) {
      return Optional.of(FOURTH_AMOUNT_LOAN);
    }
    else if (income.compareTo(GROUP_THREE_MAX_SUM_REQUIRED) > 0) {
      return Optional.of(MAX_AMOUNT_LOAN);
    }
    return Optional.empty();
  }

  public Optional<BankOffer> makeDurationOffer(BigDecimal amount,
      PersonalInformation personalInformation,
      FinancialStatus financialStatus)
  {

    if (personalInformation.getAge() >= MIN_AGE_FOR_LOAN &&
        personalInformation.getAge() <= GROUP_ONE_MAX_AGE) {
      if (financialStatus
          .getTypeOfContract()
          .equalsIgnoreCase(CONTRACT_TYPE1)) {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, SEVENTH_GROUP_MONTHS_FOR_LOAN, MAX_MONTHS_FOR_LOAN, MIN_INTEREST));
      }
      else {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount,
            FIFTH_GROUP_MONTHS_FOR_LOAN, SEVENTH_GROUP_MONTHS_FOR_LOAN,
            MIN_INTEREST));
      }
    }

    else if (personalInformation.getAge() >= GROUP_TWO_MIN_AGE &&
        personalInformation.getAge() <= GROUP_TWO_MAX_AGE) {
      if (financialStatus
          .getTypeOfContract()
          .equalsIgnoreCase(CONTRACT_TYPE1)) {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, SEVENTH_GROUP_MONTHS_FOR_LOAN, MAX_MONTHS_FOR_LOAN,
            MIN_INTEREST));

      }
      else {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, THIRD_GROUP_MONTHS_FOR_LOAN, SEVENTH_GROUP_MONTHS_FOR_LOAN,
            MIN_INTEREST));
      }
    }
    else if (personalInformation.getAge() >= GROUP_THREE_MIN_AGE &&
        personalInformation.getAge() <= GROUP_THREE_MAX_AGE) {
      if (financialStatus
          .getTypeOfContract()
          .equalsIgnoreCase(CONTRACT_TYPE1)) {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, SIXTH_GROUP_MONTHS_FOR_LOAN, NINTH_GROUP_MONTHS_FOR_LOAN,
            MID_INTEREST));

      }
      else {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, SECOND_GROUP_MONTHS_FOR_LOAN, SIXTH_GROUP_MONTHS_FOR_LOAN,
            MID_INTEREST));

      }
    }
    else if (personalInformation.getAge() >= GROUP_FOUR_MIN_AGE &&
        personalInformation.getAge() <= GROUP_FOUR_MAX_AGE) {
      if (financialStatus
          .getTypeOfContract()
          .equalsIgnoreCase(CONTRACT_TYPE1)) {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, FOURTH_GROUP_MONTHS_FOR_LOAN, EIGHT_GROUP_MONTHS_FOR_LOAN,
            MID_INTEREST));
      }
      else {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, MIN_MONTHS_FOR_LOAN, FOURTH_GROUP_MONTHS_FOR_LOAN,
            MID_INTEREST));
      }
    }
    else if (personalInformation.getAge() >= GROUP_FIVE_MIN_AGE &&
        personalInformation.getAge() <= MAX_AGE_FOR_LOAN) {
      if (financialStatus
          .getTypeOfContract()
          .equalsIgnoreCase(CONTRACT_TYPE1)) {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, FOURTH_GROUP_MONTHS_FOR_LOAN, EIGHT_GROUP_MONTHS_FOR_LOAN, HIGH_INTEREST));
      }
      else {
        return Optional.of(new BankOffer(personalInformation.getId_client(), MIN_AMOUNT_LOAN,
            amount, MIN_MONTHS_FOR_LOAN, FOURTH_GROUP_MONTHS_FOR_LOAN, MAX_INTEREST));
      }
    }

    return Optional.empty();

  }
}

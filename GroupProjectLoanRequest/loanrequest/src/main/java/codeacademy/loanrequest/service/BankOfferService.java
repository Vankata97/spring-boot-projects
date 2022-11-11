package codeacademy.loanrequest.service;

import static codeacademy.loanrequest.utils.ExceptionMessages.BLANK_BANK_OFFER;

import codeacademy.loanrequest.dao.bank.offer.BankOfferDao;
import codeacademy.loanrequest.exceptions.TeamOneException;
import codeacademy.loanrequest.model.BankOffer;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BankOfferService
{
  public final BankOfferDao bankOfferDao;

  public BankOfferService(BankOfferDao bankOfferDao)
  {
    this.bankOfferDao = bankOfferDao;
  }


  public BankOffer findById(int id){

      if(!bankOfferDao.findById(id).isPresent()){

        throw new TeamOneException(BLANK_BANK_OFFER);
      }

     return bankOfferDao.findById(id).get();

  }

}

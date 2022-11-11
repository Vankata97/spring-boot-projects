package codeacademy.loanrequest.dao.bank.offer;

import codeacademy.loanrequest.model.BankOffer;
import java.util.Optional;

public interface BankOfferDao
{

  void save(BankOffer bankOffer);

  Optional<BankOffer> findById(int id);

  //Optional<BankOffer> findAll();
}

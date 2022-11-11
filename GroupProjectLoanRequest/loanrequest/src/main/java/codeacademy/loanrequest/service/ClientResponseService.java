package codeacademy.loanrequest.service;

import codeacademy.loanrequest.dao.bank.offer.BankOfferDaoImpl;
import codeacademy.loanrequest.dao.clientresponse.ClientResponseDaoImpl;
import codeacademy.loanrequest.dto.ClientResponse;
import codeacademy.loanrequest.model.BankOffer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class ClientResponseService
{

  private final ClientResponseDaoImpl bankResponseDao;
  private final BankOfferDaoImpl      bankOfferDao;
  private final EmailService          emailService;


  public ClientResponseService(ClientResponseDaoImpl bankResponseDao, BankOfferDaoImpl bankOfferDao,
      EmailService emailService)
  {
    this.bankResponseDao = bankResponseDao;
    this.bankOfferDao = bankOfferDao;
    this.emailService = emailService;
  }


  public void save(ClientResponse clientResponse, int id)
  {
    BankOffer bankOffer1 = bankOfferDao.findById(id).get();

    if (clientResponse.getMonths() <= bankOffer1.getMaxPeriod()
        && clientResponse.getMonths() >= bankOffer1.getMinPeriod()) {
      if (clientResponse
          .getAmount()
          .compareTo(bankOffer1.getMaxAmount()) <= 0 && clientResponse
          .getAmount()
          .compareTo(bankOffer1.getMinAmount()) >= 0) {
        double calInterest = bankOffer1.getInterest() / 100;

        BigDecimal paymentNoInterest = clientResponse
            .getAmount()
            .divide(
                new BigDecimal(clientResponse.getMonths()), 2, RoundingMode.UP);

        BigDecimal totalAmountWithInterest =
            clientResponse
                .getAmount()
                .add(clientResponse
                    .getAmount()
                    .multiply(new BigDecimal(calInterest))).setScale(2,RoundingMode.UP);
        BigDecimal paymentWithInterest =
            totalAmountWithInterest.divide(new BigDecimal(clientResponse.getMonths()), 2, RoundingMode.UP);

        clientResponse.setId_client(id);
        bankResponseDao.save(clientResponse);
        emailService.acceptMail(id, clientResponse.getAmount(), paymentNoInterest, paymentWithInterest,
            totalAmountWithInterest, bankOffer1.getInterest(), clientResponse.getMonths());

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

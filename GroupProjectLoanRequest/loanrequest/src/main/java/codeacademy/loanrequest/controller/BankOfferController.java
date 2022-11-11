package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.model.BankOffer;
import codeacademy.loanrequest.service.BankOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class BankOfferController
{

  public final BankOfferService bankOfferService;

  public BankOfferController(BankOfferService bankOfferService)
  {
    this.bankOfferService = bankOfferService;
  }


  @GetMapping("/bankoffer")
  @ResponseStatus(HttpStatus.OK)
  public BankOffer findById(@RequestParam int id)
  {
    return bankOfferService
        .findById(id);
  }
}

package codeacademy.loanrequest.controller;

import codeacademy.loanrequest.model.FinancialStatus;
import codeacademy.loanrequest.service.FinancialStatusService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
public class FinancialStatusController
{

  private final FinancialStatusService financialStatusService;

  public FinancialStatusController(FinancialStatusService financialStatusService)
  {
    this.financialStatusService = financialStatusService;
  }


  @PostMapping("/financialstatus")
  @ResponseStatus(HttpStatus.CREATED)
  public void createFinancialStatus(@Valid @RequestBody FinancialStatus financialStatus,
      @RequestParam int id)
  {
    financialStatusService.createFinancialStatus(financialStatus, id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/financialstatus")
  @ResponseStatus(HttpStatus.OK)
  public List<FinancialStatus> findAllFinancialStatus()
  {
    return financialStatusService.findAllFinancialStatus();
  }
}

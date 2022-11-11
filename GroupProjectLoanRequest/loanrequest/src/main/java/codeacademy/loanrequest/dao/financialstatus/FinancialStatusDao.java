package codeacademy.loanrequest.dao.financialstatus;

import codeacademy.loanrequest.model.FinancialStatus;
import java.util.List;


public interface FinancialStatusDao
{

  void save(FinancialStatus financialStatus);

  List<FinancialStatus> getAllFinancialStatus();

}

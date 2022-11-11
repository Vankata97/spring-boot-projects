package codeacademy.loanrequest.dao.financialstatus;

import codeacademy.loanrequest.model.FinancialStatus;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class FinancialStatusDaoImpl implements FinancialStatusDao
{

  public final NamedParameterJdbcOperations jdbcTemplate;

  public FinancialStatusDaoImpl(NamedParameterJdbcOperations jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(FinancialStatus loanReq)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", loanReq.getId_client())
        .addValue("jobTitle", loanReq.getJobTitle())
        .addValue("typeOfContract", loanReq.getTypeOfContract())
        .addValue("monthlySalary", loanReq.getMonthlySalary())
        .addValue("otherIncomes", loanReq.getOtherIncomes())
        .addValue("otherLoans", loanReq.getOtherLoans());

    String sql = ""
        + "INSERT INTO FINANCIAL_STATUS(      "
        + "id_client,                         "
        + "JOB_POSITION,                      "
        + "TYPE_OF_CONTRACT,                  "
        + "MONTHLY_SALARY,                    "
        + "OTHER_INCOMES,                     "
        + "OTHER_LOANS)                       "
        + "VALUES(:id_client,                 "
        + "        :jobTitle,                 "
        + "        :typeOfContract,           "
        + "        :monthlySalary,            "
        + "        :otherIncomes,             "
        + "        :otherLoans)               ";

    jdbcTemplate.update(sql, params);
  }


  @Override
  public List<FinancialStatus> getAllFinancialStatus()
  {
    return jdbcTemplate.query(
        "   SELECT id_client,   "
            + "job_position,        "
            + "type_of_contract,    "
            + "monthly_salary,      "
            + "other_incomes,       "
            + "other_loans          "
            + "FROM FINANCIAL_STATUS",
        (rs, rowNum) ->
            new FinancialStatus(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getBigDecimal(4),
                rs.getBigDecimal(5),
                rs.getBigDecimal(6)));
  }
}

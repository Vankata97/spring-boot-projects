package codeacademy.loanrequest.dao.bank.offer;

import codeacademy.loanrequest.model.BankOffer;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BankOfferDaoImpl implements BankOfferDao
{


  private final NamedParameterJdbcOperations jdbcTemplate;

  public BankOfferDaoImpl(NamedParameterJdbcOperations jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(BankOffer bankOffer)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", bankOffer.getId_client())
        .addValue("minAmount", bankOffer.getMinAmount())
        .addValue("maxAmount", bankOffer.getMaxAmount())
        .addValue("minPeriod", bankOffer.getMinPeriod())
        .addValue("maxPeriod", bankOffer.getMaxPeriod())
        .addValue("Interest", bankOffer.getInterest());

    String sql = ""
        + "INSERT INTO BANK_OFFER(id_client,"
        + "min_Amount,                      "
        + "max_Amount,                      "
        + "min_months,                      "
        + "max_months,                      "
        + "interest                         "
        + ") values(:id_client,             "
        + ":minAmount,                      "
        + ":maxAmount,                      "
        + ":minPeriod,                      "
        + ":maxPeriod,                      "
        + ":Interest)                       ";
    jdbcTemplate.update(sql, params);
  }

  @Override
  public Optional<BankOffer> findById(int id)
  {
    try {
      SqlParameterSource params = new MapSqlParameterSource().addValue("id_client", id);
      return jdbcTemplate.queryForObject(""
              + "Select id_client,          "
              + "min_amount,                "
              + "max_amount,                "
              + "min_months,                "
              + "max_months,                "
              + "interest from bank_offer   "
              + "where id_client =:id_client"
          , params, (rs, rowNum) -> Optional.of(
              new BankOffer(rs.getInt(1),
                  rs.getBigDecimal(2),
                  rs.getBigDecimal(3),
                  rs.getInt(4),
                  rs.getInt(5),
                  rs.getDouble(6))));
    }
    catch (EmptyResultDataAccessException emptyResDAE) {
      return Optional.empty();
    }
  }
}



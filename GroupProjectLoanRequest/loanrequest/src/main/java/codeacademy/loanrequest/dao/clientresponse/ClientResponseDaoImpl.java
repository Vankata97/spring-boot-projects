package codeacademy.loanrequest.dao.clientresponse;

import codeacademy.loanrequest.dto.ClientResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ClientResponseDaoImpl implements ClientResponseDao
{

  private final NamedParameterJdbcOperations jdbcTemplate;

  public ClientResponseDaoImpl(NamedParameterJdbcOperations jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public void save(ClientResponse clientResponse)
  {
    MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", clientResponse.getId_client())
        .addValue("amount", clientResponse.getAmount())
        .addValue("months", clientResponse.getMonths());
    String sql =
        "      INSERT INTO BANK_OFFER_RESPONSE("
            + "id_client,                      "
            + "amount,                         "
            + "months)                         "
            + "VALUES(:id_client,              "
            + ":amount,                        "
            + ":months)                        ";
    jdbcTemplate.update(sql, params);
  }

}

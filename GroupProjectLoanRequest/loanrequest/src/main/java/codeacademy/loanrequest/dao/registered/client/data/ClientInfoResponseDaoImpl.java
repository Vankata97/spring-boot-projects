package codeacademy.loanrequest.dao.registered.client.data;

import codeacademy.loanrequest.dto.ClientInfoResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ClientInfoResponseDaoImpl implements ClientInfoResponseDao
{

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  public ClientInfoResponseDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
  {
    this.namedParameterJdbcOperations = namedParameterJdbcOperations;
  }

  @Override
  public ClientInfoResponse findById(int id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("id_client", id);
    return namedParameterJdbcOperations.queryForObject(""
            + "SELECT E_MAIL_RC, FIRSTNAME, LASTNAME, PHONE, "
            + "ADDRESS, AMOUNT, MONTHS                       "
            + " FROM CLIENT_INFO_RESPONSE                    "
            + "WHERE id_client=:id_client                    ",
        param, (rs, rowNum) -> new ClientInfoResponse(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getBigDecimal(6),
            rs.getInt(7)));

  }
}

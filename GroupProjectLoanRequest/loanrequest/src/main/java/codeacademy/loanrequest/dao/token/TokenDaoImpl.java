package codeacademy.loanrequest.dao.token;

import codeacademy.loanrequest.model.Token;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl implements TokenDao
{

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public TokenDaoImpl(NamedParameterJdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(Token token)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", token.getClient_id())
        .addValue("token", token.getToken());

    String sql = ""
        + "INSERT INTO REGISTRATION_TOKEN"
        + "(id_client,                   "
        + "token)                        "
        + "VALUES                        "
        + "(:id_client,                  "
        + ":token)                       ";

    jdbcTemplate.update(sql, params);
  }

  @Override
  public Optional<Token> getById(int id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("id_client", id);
      String sql = ""
          + "SELECT id_client, token_id, token "
          + "FROM REGISTRATION_TOKEN           "
          + "      WHERE id_client=:id_client  ";

      return jdbcTemplate.queryForObject(sql, sqlParam,
          (rs, rowNum) -> Optional.of(
              new Token(rs.getInt(1),
                  rs.getInt(2),
                  rs.getString(3))));
    }

    catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }
}

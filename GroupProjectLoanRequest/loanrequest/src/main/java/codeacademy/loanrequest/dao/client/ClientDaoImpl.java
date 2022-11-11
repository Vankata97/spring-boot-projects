package codeacademy.loanrequest.dao.client;

import codeacademy.loanrequest.model.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDao
{


  private final NamedParameterJdbcOperations jdbcTemplate;

  public ClientDaoImpl(NamedParameterJdbcOperations jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public void save(Client client)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("e_mail_rc", client.getE_mail_rc());

    String sql = ""
        + "INSERT INTO REGISTERED_CLIENTS("
        + "e_mail_rc)                     "
        + "VALUES(:e_mail_rc)             ";

    jdbcTemplate.update(sql, params);
  }

  @Override
  public void update(Client client)
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource()
        .addValue("id_client", client.getId_client())
        .addValue("e_mail_rc", client.getE_mail_rc());

    jdbcTemplate.update(""
            + "UPDATE REGISTERED_CLIENTS SET "
            + "e_mail_rc=:e_mail_rc          "
            + "WHERE                         "
            + "id_client=:id_client          "
        , sqlParam);
  }

  @Override
  public void delete(int id)
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource()
        .addValue("id_client", id);
    jdbcTemplate.update("DELETE FROM REGISTERED_CLIENTS WHERE id_client=:id_client", sqlParam);
  }

  @Override
  public Optional<Client> findById(int id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("id_client", id);
      return jdbcTemplate.queryForObject(""
              + "SELECT ID_CLIENT, E_MAIL_RC FROM REGISTERED_CLIENTS "
              + "WHERE id_client=:id_client                          "
          , sqlParam,
          (rs, rowNum) -> Optional.of(new Client(
              rs.getInt(1),
              rs.getString(2))));
    }
    catch (EmptyResultDataAccessException emptyResDAE) {
      return Optional.empty();
    }
  }

  @Override
  public List<Client> findAll()
  {
    return jdbcTemplate.query("SELECT ID_CLIENT, E_MAIL_RC FROM REGISTERED_CLIENTS",
        BeanPropertyRowMapper.newInstance(Client.class));
  }

  @Override
  public Optional<Client> findByEmail(String email)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("e_mail_rc", email);
      Optional<Client> client = jdbcTemplate.queryForObject(""
              + "SELECT ID_CLIENT, E_MAIL_RC FROM REGISTERED_CLIENTS "
              + "WHERE e_mail_rc=:e_mail_rc                          "
          , sqlParam,
          (rs, rowNum) -> Optional.of(new Client(
              rs.getInt(1),
              rs.getString(2))));
      return client;
    }
    catch (EmptyResultDataAccessException emptyResDAE) {
      return Optional.empty();
    }
  }
}

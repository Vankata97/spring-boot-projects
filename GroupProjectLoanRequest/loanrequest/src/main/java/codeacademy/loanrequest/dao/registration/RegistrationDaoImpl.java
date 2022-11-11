package codeacademy.loanrequest.dao.registration;

import codeacademy.loanrequest.model.Registration;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

//@SuppressWarnings({})
@Repository
public class RegistrationDaoImpl implements RegistrationDao
{

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public RegistrationDaoImpl(NamedParameterJdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(Registration registration)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", registration.getClient_id())
        .addValue("username", registration.getUsername())
        .addValue("password", registration.getPassword());

    String sql = ""
        + "INSERT INTO REGISTRATION_FORM(       "
        + "id_client,                           "
        + "USERNAME,PASSWORD) VALUES(:id_client,"
        + "        :username, :password)        ";

    jdbcTemplate.update(sql, params);
  }

  @Override
  public Optional<Registration> getById(int id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("id_client", id);
      return jdbcTemplate.queryForObject(""
              + "SELECT id_client, username, password, ROLE"
              + " FROM REGISTRATION_FORM                   "
              + "WHERE id_client=:id_client                "
          , sqlParam,
          (rs, rowNum) -> Optional.of(new Registration(
              rs.getInt(1),
              rs.getString(2),
              rs.getString(3),
              rs.getObject(4, Registration.Role.class))));
    }
    catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }

  }

  @Override
  public Optional<Registration> getByName(String name)
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource()
        .addValue("username", name);
    try {
      return jdbcTemplate.queryForObject(""
              + "SELECT id_client, username, password, role"
              + " FROM REGISTRATION_FORM                   "
              + "WHERE username=:username                  "
          , sqlParam,
          (rs, rowNum) -> Optional.of(new Registration(
              rs.getInt(1),
              rs.getString(2),
              rs.getString(3),
              rs.getObject(4, Registration.Role.class))));
    }
    catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public void update(Registration registration)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", registration.getClient_id())
        .addValue("role", registration
            .getRole()
            .name());

    String sql = "UPDATE REGISTRATION_FORM SET ROLE=:role where ID_CLIENT=:id_client";

    jdbcTemplate.update(sql, params);
  }
}

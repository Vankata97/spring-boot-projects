package codeacademy.loanrequest.dao.personalinformation;


import codeacademy.loanrequest.model.PersonalInformation;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalInformationDaoImpl implements PersonalInformationDao
{

  private final NamedParameterJdbcOperations jdbcTemplate;

  public PersonalInformationDaoImpl(NamedParameterJdbcOperations jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public void save(PersonalInformation personalInformation)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id_client", personalInformation.getId_client())
        .addValue("firstname", personalInformation.getFirstName())
        .addValue("lastname", personalInformation.getLastName())
        .addValue("phone", personalInformation.getPhone())
        .addValue("address", personalInformation.getAddress())
        .addValue("citizenship", personalInformation.getCitizenship())
        .addValue("age", personalInformation.getAge())
        .addValue("marital_status", personalInformation.getMarital_status())
        .addValue("children", personalInformation.getChildren());

    String sql = ""
        + "INSERT INTO PERSONAL_INFORMATION(      "
        + "id_client,                             "
        + "firstname,                             "
        + "lastname,                              "
        + "phone,                                 "
        + "address,                               "
        + "citizenship,                           "
        + "age,                                   "
        + "marital_status,                        "
        + "children)                              "
        + "VALUES(                                "
        + ":id_client,                            "
        + ":firstname,                            "
        + ":lastname,                             "
        + ":phone,                                "
        + ":address,                              "
        + ":citizenship,                          "
        + ":age,                                  "
        + ":marital_status,                       "
        + ":children)                             ";

    jdbcTemplate.update(sql, params);
  }

  @Override
  public void update(PersonalInformation personalInformation)
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource()
        .addValue("id_client", personalInformation.getId_client())
        .addValue("firstname", personalInformation.getFirstName())
        .addValue("lastname", personalInformation.getLastName())
        .addValue("phone", personalInformation.getPhone())
        .addValue("address", personalInformation.getAddress())
        .addValue("citizenship", personalInformation.getCitizenship())
        .addValue("age", personalInformation.getAge())
        .addValue("marital_status", personalInformation.getMarital_status())
        .addValue("children", personalInformation.getChildren());

    jdbcTemplate.update(""
            + "UPDATE PERSONAL_INFORMATION         "
            + "  SET                               "
            + "  id_client=:id_client,             "
            + "  firstname=:firstname,             "
            + "  lastname=:lastname,               "
            + "  phone=:phone,                     "
            + "  address=:address,                 "
            + "  citizenship=:citizenship,         "
            + "  age=:age,                         "
            + "  marital_status=:marital_status,   "
            + "  children=:children                "
            + "  WHERE                             "
            + "  id_client=:id_client              "
        , sqlParam);

  }

  @Override
  public void delete(int id)
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource()
        .addValue("id_client", id);
    jdbcTemplate.update("DELETE FROM PERSONAL_INFORMATION WHERE id_client=:id_client", sqlParam);
  }

  @Override
  public Optional<PersonalInformation> findById(int id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("id_client", id);
      return jdbcTemplate.queryForObject(""
              + "SELECT ID_CLIENT, FIRSTNAME, LASTNAME, PHONE, ADDRESS, CITIZENSHIP, "
              + "AGE, MARITAL_STATUS, CHILDREN                                       "
              + "FROM PERSONAL_INFORMATION WHERE id_client=:id_client                ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new PersonalInformation(
              rs.getInt(1),
              rs.getString(2),
              rs.getString(3),
              rs.getString(4),
              rs.getString(5),
              rs.getString(6),
              rs.getInt(7),
              rs.getString(8),
              rs.getInt(9))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<PersonalInformation> findAll()
  {
    return jdbcTemplate.query(""
            + "SELECT ID_CLIENT, FIRSTNAME, LASTNAME, PHONE, ADDRESS,"
            + " CITIZENSHIP, AGE, MARITAL_STATUS, CHILDREN           "
            + "FROM PERSONAL_INFORMATION                             ",
        BeanPropertyRowMapper.newInstance(PersonalInformation.class));
  }


}

package codeacademy.loanrequest.dao.file;

import codeacademy.loanrequest.model.File;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class FileDaoImpl implements FileDao
{



  private final NamedParameterJdbcTemplate jdbcTemplate;

  public FileDaoImpl(NamedParameterJdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public void save(File file)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", file.getName())
        .addValue("type", file.getType())
        .addValue("data", file.getData())
        .addValue("ID_CLIENT", file.getId());
    String sql =
        "Insert Into FILE_DATA"
            + "(name, type, data,ID_CLIENT)"
            + " VALUES (:name, :type, :data, :ID_CLIENT)";
    jdbcTemplate.update(sql, params);
}

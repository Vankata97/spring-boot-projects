package codeacademy.loanrequest.dao.token;

import codeacademy.loanrequest.model.Token;
import java.util.Optional;

public interface TokenDao
{

  void save(Token token);

  Optional<Token> getById(int id);
}

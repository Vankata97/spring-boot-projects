package codeacademy.loanrequest.dao.client;

import codeacademy.loanrequest.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientDao
{

  void save(Client client);

  void update(Client client);

  void delete(int id);

  Optional<Client> findById(int id);

  List<Client> findAll();

  Optional<Client> findByEmail(String email);
}

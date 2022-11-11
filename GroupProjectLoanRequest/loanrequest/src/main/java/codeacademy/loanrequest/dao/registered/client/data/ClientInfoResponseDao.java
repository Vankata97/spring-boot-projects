package codeacademy.loanrequest.dao.registered.client.data;

import codeacademy.loanrequest.dto.ClientInfoResponse;

public interface ClientInfoResponseDao
{

  ClientInfoResponse findById(int id);

}

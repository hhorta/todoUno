package com.todouno.kardex.services.client;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClient() throws ServiceException;

    ClientDTO getClientById(Integer id) throws ServiceException;

    List<ClientDTO> findClientByDocument(String document) throws ServiceException;

    ClientDTO createClient(ClientDTO clientDTO) throws ServiceException;

    ClientDTO updateClient(ClientDTO clientDTO) throws ServiceException;

}

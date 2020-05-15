package com.todouno.kardex.repository.client.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.ClientEntity;

import java.util.List;

public interface ClientRepositoryFacade {


    List<ClientEntity> getAllClient() throws ServiceException;

    ClientEntity getClientById(Integer id) throws ServiceException;

    ClientEntity getClientByDocument(String description) throws ServiceException;

    List<ClientEntity> findClientByDocument(String description) throws ServiceException;

    ClientEntity createClient(ClientEntity entity) throws ServiceException;

    ClientEntity updateClient(ClientEntity entity) throws ServiceException;

    void deleteClient(Integer id) throws ServiceException;
}

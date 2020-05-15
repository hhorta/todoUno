package com.todouno.kardex.services.client.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.util.Util;
import com.todouno.kardex.domain.dto.ClientDTO;
import com.todouno.kardex.domain.entity.ClientEntity;
import com.todouno.kardex.domain.enums.Status;
import com.todouno.kardex.repository.client.facade.ClientRepositoryFacade;
import com.todouno.kardex.services.client.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
class ClientServiceImpl implements ClientService {

    private final ClientRepositoryFacade repositoryFacade;

    ClientServiceImpl(ClientRepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }


    @Override
    public List<ClientDTO> getAllClient() throws ServiceException {

        List<ClientEntity> companyList = repositoryFacade.getAllClient();
        return companyList.stream().map(this::mapperDTOClientEntity).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Integer id) throws ServiceException {
        return mapperDTOClientEntity(repositoryFacade.getClientById(id));
    }

    @Override
    public  List<ClientDTO> findClientByDocument(String document) throws ServiceException {
        List<ClientEntity> companyList = repositoryFacade.findClientByDocument(document);
            return companyList.stream().map(this::mapperDTOClientEntity).collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) throws ServiceException {
        return mapperDTOClientEntity(repositoryFacade.createClient(mapperClientDTOtoEntity(clientDTO)));
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) throws ServiceException {
        ClientEntity client = repositoryFacade.getClientByDocument(clientDTO.getDocument());
        client.setName(defaultIfNull(clientDTO.getName(), client.getName()));
        client.setSurname(defaultIfNull(clientDTO.getSurname(), client.getSurname()));
        client.setAddress(defaultIfNull(clientDTO.getAddress(), client.getAddress()));
        client.setEmail(defaultIfNull(clientDTO.getEmail(), client.getEmail()));
        client.setPhone(defaultIfNull(clientDTO.getPhone(), client.getPhone()));
        return mapperDTOClientEntity(repositoryFacade.updateClient((client)));
    }


    private ClientDTO mapperDTOClientEntity(ClientEntity entity) {
        return ClientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .document(entity.getDocument())
                .build();
    }

    public ClientEntity mapperClientDTOtoEntity(ClientDTO clientDTO) {
        ClientEntity client = new ClientEntity();
        client.setName(clientDTO.getName());
        client.setDocument(clientDTO.getDocument());
        client.setAddress(clientDTO.getAddress());
        client.setDocumentType(clientDTO.getDocumentType());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setStatus(Status.ACTIVE);
        client.setSurname(clientDTO.getSurname());
        client.setCreatedAt(Util.getDateActual());
        return client;
    }


}

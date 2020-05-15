package com.todouno.kardex.repository.client.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.ClientEntity;
import com.todouno.kardex.repository.client.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ClientRepositoryFacadeImpl implements ClientRepositoryFacade {

    private final ClientRepository clientRepository;

    public ClientRepositoryFacadeImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientEntity> getAllClient() throws ServiceException {
        try {
            return Optional.ofNullable(clientRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de Cliente", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public ClientEntity getClientById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(clientRepository.findClientEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Cliente por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la Cliente por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public ClientEntity getClientByDocument(String document) throws ServiceException {
        try {
            return Optional.ofNullable(clientRepository.findClientEntitiesByDocument(document))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Cliente no encontrada por codigo"));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la Cliente por documento", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public List<ClientEntity> findClientByDocument(String document) throws ServiceException {
        try {
            return Optional.ofNullable(clientRepository.findClientByDocument(document))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Cliente no encontrada por codigo"));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la Cliente por documento", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public ClientEntity createClient(ClientEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public ClientEntity updateClient(ClientEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public void deleteClient(Integer id) throws ServiceException {
        Optional<ClientEntity> category = clientRepository.findById(id);
        if (category.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del Cliente no existe");
        }
    }

    @NotNull
    private ClientEntity executorCreate(ClientEntity entity) throws ServiceException {
        try {
            return clientRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de Cliente por documento", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

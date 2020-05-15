package com.todouno.kardex.repository.orderDetails.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.OrderDetailsEntity;
import com.todouno.kardex.repository.orderDetails.OrderDetailsRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OrderDetailsRepositoryFacadeImpl implements OrderDetailsRepositoryFacade {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsRepositoryFacadeImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetailsEntity> getAllDetails() throws ServiceException {
        try {
            return Optional.ofNullable(orderDetailsRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de detalles", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public OrderDetailsEntity getDetailsById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(orderDetailsRepository.findDetailsEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
                    throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public List<OrderDetailsEntity> getDetailsByCode(String code) throws ServiceException {
        try {
            return Optional.ofNullable(orderDetailsRepository.getDetailsByCode(code))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public OrderDetailsEntity getDetailsByDescription(String code) throws ServiceException {
        try {
            return Optional.ofNullable(orderDetailsRepository.findDetailsEntitiesByCode(code))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria no encontrada por descripción"));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public OrderDetailsEntity createDetails(OrderDetailsEntity entity) throws ServiceException {
            return executorCreate(entity);
    }

    @Override
    public OrderDetailsEntity updateDetails(OrderDetailsEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public void deleteDetails(Integer id) throws ServiceException {
        Optional<OrderDetailsEntity> details = orderDetailsRepository.findById(id);
        if (details.isPresent()) {
            orderDetailsRepository.deleteById(id);
        } else {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del detalle no existe");
        }
    }

    @NotNull
    private OrderDetailsEntity executorCreate(OrderDetailsEntity entity) throws ServiceException {
        try {
            return orderDetailsRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de detalle por codigo",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

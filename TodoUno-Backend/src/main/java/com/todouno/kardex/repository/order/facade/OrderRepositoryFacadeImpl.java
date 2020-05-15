package com.todouno.kardex.repository.order.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.OrderEntity;
import com.todouno.kardex.repository.order.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OrderRepositoryFacadeImpl implements OrderRepositoryFacade {

    private final OrderRepository orderRepository;

    public OrderRepositoryFacadeImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderEntity> getAllOrder() throws ServiceException {
        try {
            return Optional.of(orderRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de detalles", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public OrderEntity getOrderById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(orderRepository.findOrderEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public List<OrderEntity> findOrderListById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(orderRepository.findOrderListById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public OrderEntity create(OrderEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public OrderEntity update(OrderEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @NotNull
    private OrderEntity executorCreate(OrderEntity entity) throws ServiceException {
        try {
            return orderRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de detalle por codigo", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

package com.todouno.kardex.repository.order.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.OrderEntity;

import java.util.List;

public interface OrderRepositoryFacade {

    List<OrderEntity> getAllOrder() throws ServiceException;

    OrderEntity getOrderById(Integer id) throws ServiceException;

    List<OrderEntity> findOrderListById(Integer id) throws ServiceException;

    OrderEntity create(OrderEntity entity) throws ServiceException;

    OrderEntity update(OrderEntity entity) throws ServiceException;
}

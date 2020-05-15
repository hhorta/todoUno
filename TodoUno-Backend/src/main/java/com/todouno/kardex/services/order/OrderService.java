package com.todouno.kardex.services.order;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.OrderDTO;
import com.todouno.kardex.domain.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrder() throws ServiceException;

    OrderDTO getOrderById(Integer id) throws ServiceException;

    List<OrderDTO> findOrderListById(Integer id) throws ServiceException;

    OrderDTO create(OrderDTO orderDTO) throws ServiceException;

    OrderDTO update(OrderDTO orderDTO) throws ServiceException;
}

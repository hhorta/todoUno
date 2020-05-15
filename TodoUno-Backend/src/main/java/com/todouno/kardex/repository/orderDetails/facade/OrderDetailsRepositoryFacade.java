package com.todouno.kardex.repository.orderDetails.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsRepositoryFacade {


    List<OrderDetailsEntity> getAllDetails() throws ServiceException;

    OrderDetailsEntity getDetailsById(Integer id) throws ServiceException;

    List<OrderDetailsEntity> getDetailsByCode (String code) throws ServiceException;

    OrderDetailsEntity getDetailsByDescription(String description) throws ServiceException;

    OrderDetailsEntity createDetails(OrderDetailsEntity entity) throws ServiceException;

    OrderDetailsEntity updateDetails(OrderDetailsEntity entity) throws ServiceException;

    void deleteDetails(Integer id) throws ServiceException;
}

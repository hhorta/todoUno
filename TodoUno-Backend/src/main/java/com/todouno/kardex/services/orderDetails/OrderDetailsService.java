package com.todouno.kardex.services.orderDetails;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.OrderDetailsDTO;
import com.todouno.kardex.domain.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsService {

    List<OrderDetailsDTO> getAllDetails() throws ServiceException;

    OrderDetailsDTO getDetailsById(Integer id) throws ServiceException;

    List<OrderDetailsDTO> getDetailsByCode (String code) throws ServiceException;

    OrderDetailsDTO create(OrderDetailsDTO orderDetailsDTO) throws ServiceException;

    String create(List<OrderDetailsDTO> orderDetailsDTOList) throws ServiceException;

    OrderDetailsDTO update(OrderDetailsDTO orderDetailsDTO) throws ServiceException;

}

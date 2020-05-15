package com.todouno.kardex.services.order.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.util.Util;
import com.todouno.kardex.domain.dto.*;
import com.todouno.kardex.domain.entity.ClientEntity;
import com.todouno.kardex.domain.entity.EmployeeEntity;
import com.todouno.kardex.domain.entity.OrderDetailsEntity;
import com.todouno.kardex.domain.entity.OrderEntity;
import com.todouno.kardex.domain.enums.Status;
import com.todouno.kardex.repository.client.facade.ClientRepositoryFacade;
import com.todouno.kardex.repository.employee.facade.EmployeeRepositoryFacade;
import com.todouno.kardex.repository.order.facade.OrderRepositoryFacade;
import com.todouno.kardex.repository.orderDetails.facade.OrderDetailsRepositoryFacade;
import com.todouno.kardex.services.order.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryFacade orderRepositoryFacade;
    private final ClientRepositoryFacade clientRepositoryFacade;
    private final EmployeeRepositoryFacade employeeRepositoryFacade;
    private final OrderDetailsRepositoryFacade orderDetailsRepositoryFacade;

    public OrderServiceImpl(OrderRepositoryFacade orderRepositoryFacade, ClientRepositoryFacade clientRepositoryFacade, EmployeeRepositoryFacade employeeRepositoryFacade, OrderDetailsRepositoryFacade orderDetailsRepositoryFacade) {
        this.orderRepositoryFacade = orderRepositoryFacade;
        this.clientRepositoryFacade = clientRepositoryFacade;
        this.employeeRepositoryFacade = employeeRepositoryFacade;
        this.orderDetailsRepositoryFacade = orderDetailsRepositoryFacade;
    }

    @Override
    public List<OrderDTO> getAllOrder() throws ServiceException {
        List<OrderEntity> list = orderRepositoryFacade.getAllOrder();
        return list.stream().map(this::mapperDTOOrderEntityList).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) throws ServiceException {
        return mapperDTOOrderEntity(orderRepositoryFacade.getOrderById(id));
    }

    @Override
    public List<OrderDTO> findOrderListById(Integer id) throws ServiceException {
        List<OrderEntity> order = orderRepositoryFacade.findOrderListById(id);
        return order.stream().map(this::mapperDTOOrderEntity).collect(Collectors.toList());
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) throws ServiceException {
        return mapperDTOOrderEntity(orderRepositoryFacade.create(mapperEntityOrderDTO(orderDTO)));
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) throws ServiceException {
        OrderEntity order = orderRepositoryFacade.getOrderById(orderDTO.getId());
        return mapperDTOOrderEntity(orderRepositoryFacade.update(order));
    }

    private OrderDTO mapperDTOOrderEntity(OrderEntity entity){
        return OrderDTO.builder()
                .id(entity.getId())
                .creatAt(entity.getCreatAt())
                .status(entity.getStatus())
                .numOrderDetails(entity.getNumOrderDetails())
                .clientByClientId(new ClientDTO(entity.getClientByClientId().getId(),entity.getClientByClientId().getDocument(),entity.getClientByClientId().getName(),entity.getClientByClientId().getSurname(),entity.getClientByClientId().getAddress(),entity.getClientByClientId().getPhone(),entity.getClientByClientId().getEmail(),entity.getClientByClientId().getStatus(),entity.getClientByClientId().getDocumentType()))
                .employeeByEmployeeId(new EmployeeDTO(entity.getEmployeeByEmployeeId().getId(),entity.getEmployeeByEmployeeId().getDocument(),entity.getEmployeeByEmployeeId().getName(),entity.getEmployeeByEmployeeId().getSurname(),entity.getEmployeeByEmployeeId().getPhone(),entity.getEmployeeByEmployeeId().getStatus(),entity.getEmployeeByEmployeeId().getDocumentType()))
                .orderByIdOrder(new OrderDetailsDTO(entity.getOrderByIdOrder().getId(),entity.getOrderByIdOrder().getCode(),entity.getOrderByIdOrder().getCreatedAt(),entity.getOrderByIdOrder().getDescription(),entity.getOrderByIdOrder().getQuantity(),new ProductDTO(entity.getOrderByIdOrder().getId(),entity.getOrderByIdOrder().getCode(),new CategoryDTO(entity.getOrderByIdOrder().getProductId().getCategoryId().getId(),entity.getOrderByIdOrder().getProductId().getCategoryId().getDescription()),entity.getOrderByIdOrder().getStatus())))
                .build();
    }

    private OrderDTO mapperDTOOrderEntityList(OrderEntity entity){
        //OrderDetailsEntity orderDetails = orderDetailsRepositoryFacade.getDetailsById()

        return OrderDTO.builder()
                .id(entity.getId())
                .creatAt(entity.getCreatAt())
                .status(entity.getStatus())
                .numOrderDetails(entity.getNumOrderDetails())
                .clientByClientId(new ClientDTO(entity.getClientByClientId().getId(),entity.getClientByClientId().getDocument(),entity.getClientByClientId().getName(),entity.getClientByClientId().getSurname(),entity.getClientByClientId().getAddress(),entity.getClientByClientId().getPhone(),entity.getClientByClientId().getEmail(),entity.getClientByClientId().getStatus(),entity.getClientByClientId().getDocumentType()))
                .employeeByEmployeeId(new EmployeeDTO(entity.getEmployeeByEmployeeId().getId(),entity.getEmployeeByEmployeeId().getDocument(),entity.getEmployeeByEmployeeId().getName(),entity.getEmployeeByEmployeeId().getSurname(),entity.getEmployeeByEmployeeId().getPhone(),entity.getEmployeeByEmployeeId().getStatus(),entity.getEmployeeByEmployeeId().getDocumentType()))
                .orderByIdOrder(new OrderDetailsDTO(entity.getOrderByIdOrder().getId(),entity.getOrderByIdOrder().getCode(),entity.getOrderByIdOrder().getCreatedAt(),entity.getOrderByIdOrder().getDescription(),entity.getOrderByIdOrder().getQuantity(),new ProductDTO(entity.getOrderByIdOrder().getId(),entity.getOrderByIdOrder().getCode())))
                .build();
    }

    private OrderEntity mapperEntityOrderDTO(OrderDTO orderDTO) throws ServiceException {
        OrderEntity order = new OrderEntity();
        ClientEntity client = clientRepositoryFacade.getClientByDocument(orderDTO.getClientByClientId().getDocument());
        EmployeeEntity employee = employeeRepositoryFacade.getEmployeeByDocument(orderDTO.getEmployeeByEmployeeId().getDocument());
        OrderDetailsEntity orderDetails = orderDetailsRepositoryFacade.getDetailsById(orderDTO.getOrderByIdOrder().getId());
        order.setCreatAt(Util.getDateActual());
        order.setStatus(Status.ACTIVE);
        order.setNumOrderDetails(orderDetails.getCode());
        order.setClientByClientId(client);
        order.setEmployeeByEmployeeId(employee);
        order.setOrderByIdOrder(orderDetails);
        return order;

    }
}

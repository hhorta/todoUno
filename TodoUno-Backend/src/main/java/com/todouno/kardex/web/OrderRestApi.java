package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.ClientDTO;
import com.todouno.kardex.domain.dto.OrderDTO;
import com.todouno.kardex.domain.entity.OrderEntity;
import com.todouno.kardex.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin({"*"})
public class OrderRestApi {

    private final OrderService service;

    @Autowired
    public OrderRestApi(OrderService orderService){this.service= orderService;}

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllDetails() throws ServiceException {
        return service.getAllOrder();
    }

    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getDetailsById(@PathVariable("id") Integer id)
            throws ServiceException {
        OrderDTO entity = service.getOrderById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/order/{id}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrderById(@PathVariable("id") Integer id)
            throws ServiceException {
        return service.findOrderListById(id);
    }


    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) throws ServiceException {
        OrderDTO created = service.create(orderDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> updateDetails (@RequestBody OrderDTO orderDTO) throws ServiceException{
        OrderDTO update = service.update(orderDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }
}

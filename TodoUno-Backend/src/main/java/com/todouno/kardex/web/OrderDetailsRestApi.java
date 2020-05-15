package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.ClientDTO;
import com.todouno.kardex.domain.dto.OrderDetailsDTO;
import com.todouno.kardex.services.orderDetails.OrderDetailsService;
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
public class OrderDetailsRestApi {

    private final OrderDetailsService service;

    @Autowired
    public OrderDetailsRestApi(OrderDetailsService orderDetailsService){this.service= orderDetailsService;}

    @GetMapping(value = "/orderDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailsDTO> getAllDetails() throws ServiceException {
        return service.getAllDetails();
    }

    @GetMapping(value = "/orderDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsDTO> getDetailsById(@PathVariable("id") Integer id)
            throws ServiceException {
        OrderDetailsDTO entity = service.getDetailsById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping(value = "/orderDetails/{code}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailsDTO> getClientByDocument(@PathVariable("code") String code)
            throws ServiceException {
        return service.getDetailsByCode(code);
    }

    @PostMapping(value = "/orderDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody List<OrderDetailsDTO> orderDetailsDTO) throws ServiceException {
        String created = service.create(orderDetailsDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/orderDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsDTO> updateDetails (@RequestBody OrderDetailsDTO orderDetailsDTO) throws ServiceException{
        OrderDetailsDTO update = service.update(orderDetailsDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }
}

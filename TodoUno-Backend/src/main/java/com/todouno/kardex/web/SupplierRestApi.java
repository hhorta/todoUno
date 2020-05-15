package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.SupplierDTO;
import com.todouno.kardex.services.supplier.SupplierService;
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
public class SupplierRestApi {

    private final SupplierService service;

    @Autowired
    public SupplierRestApi(SupplierService supplierService){this.service=supplierService;}

    @GetMapping(value = "/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplierDTO> getAllSupplier() throws ServiceException {
        return service.getAllSupplier();
    }

    @GetMapping(value = "/supplier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable("id") Integer id)
            throws ServiceException {
        SupplierDTO entity = service.getSupplierById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/supplier", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierDTO> create(@RequestBody SupplierDTO supplierDTO) throws ServiceException {
        SupplierDTO created = service.createSupplier(supplierDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/supplier", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierDTO> updateSupplier (@RequestBody SupplierDTO supplierDTO) throws ServiceException{
        SupplierDTO update = service.updateSupplier(supplierDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

}

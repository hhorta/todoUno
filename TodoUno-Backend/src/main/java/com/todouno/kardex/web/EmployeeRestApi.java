package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.EmployeeDTO;
import com.todouno.kardex.services.employee.EmployeeService;
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
public class EmployeeRestApi {

    private final EmployeeService service;

    @Autowired
    public EmployeeRestApi(EmployeeService employeeService) {
        this.service = employeeService;
    }

    @GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDTO> getAllEmployee() throws ServiceException {
        return service.getAllEmployee();
    }

    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Integer id)
            throws ServiceException {
        EmployeeDTO entity = service.getEmployeeById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) throws ServiceException {
        EmployeeDTO created = service.createEmployee(employeeDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) throws ServiceException {
        EmployeeDTO update = service.updateEmployee(employeeDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }
}

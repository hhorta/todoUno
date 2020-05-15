package com.todouno.kardex.services.employee;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployee() throws ServiceException;

    EmployeeDTO getEmployeeById(Integer id) throws ServiceException;

    EmployeeDTO createEmployee(EmployeeDTO clientDTO) throws ServiceException;

    EmployeeDTO updateEmployee(EmployeeDTO clientDTO) throws ServiceException;

}

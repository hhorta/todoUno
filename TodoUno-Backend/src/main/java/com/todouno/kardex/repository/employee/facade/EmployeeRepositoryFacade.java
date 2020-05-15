package com.todouno.kardex.repository.employee.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepositoryFacade {

    List<EmployeeEntity> getAllEmployee() throws ServiceException;

    EmployeeEntity getEmployeeById(Integer id) throws ServiceException;

    EmployeeEntity getEmployeeByDocument(String description) throws ServiceException;

    EmployeeEntity createEmployee(EmployeeEntity entity) throws ServiceException;

    EmployeeEntity updateEmployee(EmployeeEntity entity) throws ServiceException;

    void deleteEmployee(Integer id) throws ServiceException;
}

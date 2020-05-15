package com.todouno.kardex.services.employee.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.EmployeeDTO;
import com.todouno.kardex.domain.entity.EmployeeEntity;
import com.todouno.kardex.domain.enums.Status;
import com.todouno.kardex.repository.employee.facade.EmployeeRepositoryFacade;
import com.todouno.kardex.services.employee.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepositoryFacade repositoryFacade;

    EmployeeServiceImpl(EmployeeRepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }


    @Override
    public List<EmployeeDTO> getAllEmployee() throws ServiceException {

        List<EmployeeEntity> companyList = repositoryFacade.getAllEmployee();
        return companyList.stream().map(this::mapperDTOEmployeeEntity).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) throws ServiceException {
        return mapperDTOEmployeeEntity(repositoryFacade.getEmployeeById(id));
    }


    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws ServiceException {
        return mapperDTOEmployeeEntity(repositoryFacade.createEmployee(mapperEmployeeDTOtoEntity(employeeDTO)));
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws ServiceException {
        EmployeeEntity employee = repositoryFacade.getEmployeeByDocument(employeeDTO.getDocument());
        employee.setName(defaultIfNull(employeeDTO.getName(), employee.getName()));
        employee.setSurname(defaultIfNull(employeeDTO.getSurname(), employee.getSurname()));
        employee.setPhone(defaultIfNull(employeeDTO.getPhone(), employee.getPhone()));
        return mapperDTOEmployeeEntity(repositoryFacade.updateEmployee((employee)));
    }


    private EmployeeDTO mapperDTOEmployeeEntity(EmployeeEntity entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .document(entity.getDocument())
                .build();
    }

    public EmployeeEntity mapperEmployeeDTOtoEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());
        employee.setDocument(employeeDTO.getDocument());
        employee.setDocumentType(employeeDTO.getDocumentType());
        employee.setPhone(employeeDTO.getPhone());
        employee.setStatus(Status.ACTIVE);
        employee.setSurname(employeeDTO.getSurname());
        return employee;
    }


}

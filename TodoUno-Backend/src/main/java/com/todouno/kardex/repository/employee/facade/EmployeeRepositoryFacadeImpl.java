package com.todouno.kardex.repository.employee.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.EmployeeEntity;
import com.todouno.kardex.repository.employee.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EmployeeRepositoryFacadeImpl implements EmployeeRepositoryFacade {

    private final EmployeeRepository employeeRepository;

    public EmployeeRepositoryFacadeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> getAllEmployee() throws ServiceException {
        try {
            return Optional.of(employeeRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de Employeee", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public EmployeeEntity getEmployeeById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(employeeRepository.findEmployeeEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Employeee por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la Employeee por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public EmployeeEntity getEmployeeByDocument(String document) throws ServiceException {
        try {
            return Optional.ofNullable(employeeRepository.findEmployeeEntitiesByDocument(document))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Employeee no encontrada por codigo"));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la Employeee por documento", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public void deleteEmployee(Integer id) throws ServiceException {
        Optional<EmployeeEntity> category = employeeRepository.findById(id);
        if (category.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del Employeee no existe");
        }
    }

    @NotNull
    private EmployeeEntity executorCreate(EmployeeEntity entity) throws ServiceException {
        try {
            return employeeRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de Employeee por documento", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

package com.todouno.kardex.repository.supplier.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.SupplierEntity;
import com.todouno.kardex.repository.supplier.SupplierRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SupplierRepositoryFacadeImpl implements SupplierRepositoryFacade {

    private final SupplierRepository supplierRepository;

    public SupplierRepositoryFacadeImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierEntity> getAllSupplier() throws ServiceException {
        try {
            return Optional.ofNullable(supplierRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de proveedor", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public SupplierEntity getSupplierById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(supplierRepository.findSupplierEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Proveedor por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la proveedor por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public SupplierEntity getSupplierByDescription(String description) throws ServiceException {
        try {
            return Optional.ofNullable(supplierRepository.findSupplierEntitiesByDescription(description))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Proveedor no encontrada por descripción"));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la proveedor por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public SupplierEntity createSupplier(SupplierEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public SupplierEntity updateSupplier(SupplierEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public void deleteSupplier(Integer id) throws ServiceException {
        Optional<SupplierEntity> category = supplierRepository.findById(id);
        if (category.isPresent()) {
            supplierRepository.deleteById(id);
        } else {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del proveedor no existe");
        }
    }

    @NotNull
    private SupplierEntity executorCreate(SupplierEntity entity) throws ServiceException {
        try {
            return supplierRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la proveedor por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

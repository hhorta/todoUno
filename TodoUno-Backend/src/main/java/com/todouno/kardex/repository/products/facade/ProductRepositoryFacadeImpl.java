package com.todouno.kardex.repository.products.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.ProductEntity;
import com.todouno.kardex.repository.products.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryFacadeImpl implements ProductRepositoryFacade {

    private final ProductRepository productRepository;

    public ProductRepositoryFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getAllProduct() throws ServiceException {
        try {
            return Optional.ofNullable(productRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de productos", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public ProductEntity getProductById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(productRepository.findProductEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Productos por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la productos por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public ProductEntity getProductByCode(String code) throws ServiceException {
        try {
            return Optional.ofNullable(productRepository.findProductEntitiesByCode(code))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "producto no encontrada por codigo " + code));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public ProductEntity createProduct(ProductEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @NotNull
    private ProductEntity executorCreate(ProductEntity entity) throws ServiceException {
        try {
            return productRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

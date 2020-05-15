package com.todouno.kardex.repository.category.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.domain.entity.CategoryEntity;
import com.todouno.kardex.repository.category.CategoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CategoryRepositoryFacadeImpl implements CategoryRepositoryFacade{

    private final CategoryRepository categoryRepository;

    public CategoryRepositoryFacadeImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getAllCategory() throws ServiceException {
        try {
            return Optional.ofNullable(categoryRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de categoria", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public CategoryEntity getCategoryById(Integer id) throws ServiceException {
        try {
            return Optional.ofNullable(categoryRepository.findCategoryEntitiesById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria por id no encontrada"));
        } catch (IllegalArgumentException | PersistenceException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por id", pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general del servicio", e);
        }
    }

    @Override
    public CategoryEntity getCategoryByDescription(String description) throws ServiceException {
        try {
            return Optional.ofNullable(categoryRepository.findCategoryEntitiesByDescription(description))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Categoria no encontrada por descripción"));
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity entity) throws ServiceException {
        return executorCreate(entity);
    }

    @Override
    public void deleteCategory(Integer id) throws ServiceException {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del usuario no existe");
        }
    }

    @NotNull
    private CategoryEntity executorCreate(CategoryEntity entity) throws ServiceException {
        try {
            return categoryRepository.save(entity);
        } catch (PersistenceException | IllegalArgumentException pe) {
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de la categoria por descripción",pe);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error general de servicio", e);
        }
    }
}

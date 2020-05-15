package com.todouno.kardex.repository.category.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.CategoryEntity;

import java.util.List;

public interface CategoryRepositoryFacade {


    List<CategoryEntity> getAllCategory() throws ServiceException;

    CategoryEntity getCategoryById(Integer id) throws ServiceException;

    CategoryEntity getCategoryByDescription(String description) throws ServiceException;

    CategoryEntity createCategory(CategoryEntity entity) throws ServiceException;

    CategoryEntity updateCategory(CategoryEntity entity) throws ServiceException;

    void deleteCategory(Integer id) throws ServiceException;
}

package com.todouno.kardex.services.category;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategory() throws ServiceException;

    CategoryDTO getCategoryById(Integer id) throws ServiceException;

    CategoryDTO createCategory(CategoryDTO companyDTO) throws ServiceException;

    CategoryDTO updateCategory(CategoryDTO companyDTO) throws ServiceException;

}

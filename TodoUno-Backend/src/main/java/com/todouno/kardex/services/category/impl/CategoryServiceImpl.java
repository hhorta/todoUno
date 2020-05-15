package com.todouno.kardex.services.category.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.util.Util;
import com.todouno.kardex.domain.dto.CategoryDTO;
import com.todouno.kardex.domain.entity.CategoryEntity;
import com.todouno.kardex.repository.category.facade.CategoryRepositoryFacade;
import com.todouno.kardex.services.category.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoryFacade repositoryFacade;

    CategoryServiceImpl(CategoryRepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }


    @Override
    public List<CategoryDTO> getAllCategory() throws ServiceException {

        List<CategoryEntity> companyList = repositoryFacade.getAllCategory();
        return companyList.stream().map(this::mapperDTOCategoryEntity).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) throws ServiceException {
        return mapperDTOCategoryEntity(repositoryFacade.getCategoryById(id));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws ServiceException {
        return mapperDTOCategoryEntity(repositoryFacade.createCategory(mapperCategoryDTOtoEntity(categoryDTO)));

    }


    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws ServiceException {
        CategoryEntity category = repositoryFacade.getCategoryById(categoryDTO.getId());
        category.setDescription(defaultIfNull(categoryDTO.getCategory(), category.getDescription()));

        return mapperDTOCategoryEntity(repositoryFacade.updateCategory((category)));
    }


    private CategoryDTO mapperDTOCategoryEntity(CategoryEntity entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .category(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }
    public CategoryEntity mapperCategoryDTOtoEntity(CategoryDTO categoryDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setDescription(categoryDTO.getCategory());
        category.setCreatedAt(Util.getDateActual());
        return category;
    }


}

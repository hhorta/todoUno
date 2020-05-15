package com.todouno.kardex.repository.products.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.ProductEntity;

import java.util.List;

public interface ProductRepositoryFacade {


    List<ProductEntity> getAllProduct() throws ServiceException;

    ProductEntity getProductById(Integer id) throws ServiceException;

    ProductEntity getProductByCode(String code) throws ServiceException;

    ProductEntity createProduct(ProductEntity entity) throws ServiceException;

    ProductEntity updateProduct(ProductEntity entity) throws ServiceException;

}

package com.todouno.kardex.services.products;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.ProductDTO;
import com.todouno.kardex.domain.entity.Items;
import com.todouno.kardex.domain.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProduct() throws ServiceException;

    ProductDTO getProductById(Integer id) throws ServiceException;

    ProductDTO getProductByCode(String id) throws ServiceException;

    ProductDTO createProduct(ProductDTO productDTO) throws ServiceException;

    ProductEntity createProduct(ProductEntity productEntity) throws ServiceException;

    ProductDTO updateProduct(ProductDTO productDTO) throws ServiceException;

    ProductDTO disableProduct(String code) throws ServiceException;

    Items getTotalByCodeAndQuantity(String code, Integer quantity) throws ServiceException;

}

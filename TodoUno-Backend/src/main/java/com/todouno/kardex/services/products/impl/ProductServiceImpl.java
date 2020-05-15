package com.todouno.kardex.services.products.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.util.Util;
import com.todouno.kardex.domain.dto.CategoryDTO;
import com.todouno.kardex.domain.dto.ProductDTO;
import com.todouno.kardex.domain.dto.SupplierDTO;
import com.todouno.kardex.domain.entity.CategoryEntity;
import com.todouno.kardex.domain.entity.Items;
import com.todouno.kardex.domain.entity.ProductEntity;
import com.todouno.kardex.domain.entity.SupplierEntity;
import com.todouno.kardex.domain.enums.Status;
import com.todouno.kardex.repository.category.facade.CategoryRepositoryFacade;
import com.todouno.kardex.repository.products.facade.ProductRepositoryFacade;
import com.todouno.kardex.repository.supplier.facade.SupplierRepositoryFacade;
import com.todouno.kardex.services.products.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class ProductServiceImpl implements ProductService {

    private final ProductRepositoryFacade repositoryFacade;

    private final CategoryRepositoryFacade categoryRepositoryFacade;

    private final SupplierRepositoryFacade supplierRepositoryFacade;


    ProductServiceImpl(ProductRepositoryFacade repositoryFacade, CategoryRepositoryFacade categoryRepositoryFacade, SupplierRepositoryFacade supplierRepositoryFacade) {
        this.repositoryFacade = repositoryFacade;
        this.categoryRepositoryFacade = categoryRepositoryFacade;
        this.supplierRepositoryFacade = supplierRepositoryFacade;

    }

    @Override
    public List<ProductDTO> getAllProduct() throws ServiceException {
        List<ProductEntity> companyList = repositoryFacade.getAllProduct();
        return companyList.stream().map(this::mapperDTOProductEntity).collect(Collectors.toList());
    }


    @Override
    public ProductDTO getProductById(Integer id) throws ServiceException {
        return mapperDTOProductEntity(repositoryFacade.getProductById(id));
    }

    @Override
    public ProductDTO getProductByCode(String code) throws ServiceException {
        return mapperDTOProductEntity(repositoryFacade.getProductByCode(code));
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws ServiceException {
        ProductEntity productEntity = mapperProductDTOtoEntity(productDTO);
        CategoryEntity categoryEntity = categoryRepositoryFacade.getCategoryById(productDTO.getCategoryId().getId());
        SupplierEntity supplierEntity = supplierRepositoryFacade.getSupplierById(productDTO.getSupplierId().getId());
        productEntity.setSupplierId(supplierEntity);
        productEntity.setCategoryId(categoryEntity);
        return mapperDTOProductEntity(repositoryFacade.createProduct(productEntity));
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) throws ServiceException {
        return repositoryFacade.createProduct(productEntity);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) throws ServiceException {
        return executorUpdateProducts(productDTO);
    }

    private ProductDTO executorUpdateProducts(@NotNull ProductDTO productDTO) throws ServiceException {
        ProductEntity product = repositoryFacade.getProductByCode(productDTO.getCode());
        CategoryEntity category = categoryRepositoryFacade.getCategoryById(productDTO.getCategoryId().getId());
        SupplierEntity supplier = supplierRepositoryFacade.getSupplierById(productDTO.getSupplierId().getId());
        product.setName(defaultIfNull(productDTO.getName(), product.getName()));
        product.setDescription(defaultIfNull(productDTO.getDescription(), product.getDescription()));
        product.setPriceUnit(defaultIfNull(productDTO.getPriceUnit(), product.getPriceUnit()));
        product.setCategoryId(category);
        product.setSupplierId(supplier);
        return mapperDTOProductEntity(repositoryFacade.updateProduct((product)));
    }

    @Override
    public ProductDTO disableProduct(String code) throws ServiceException {
        ProductEntity product = repositoryFacade.getProductByCode(code);
      /* if (product.getExistence() <= 0) {
            product.setStatus(Status.INACTIVE);
        }*/
        repositoryFacade.createProduct(product);
        return mapperDTOProductEntity(product);
    }

    @Override
    public Items getTotalByCodeAndQuantity(String code, Integer quantity) throws ServiceException {
        ProductEntity product = repositoryFacade.getProductByCode(code);
        Items items = new Items();
        items.setProduct(mapperDTOProductEntity(product));
        items.setQuantity(quantity);
        items.getTotal();
        return items;
    }

    public ProductEntity mapperProductDTOtoEntity(@NotNull ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setCode(Util.getCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCreatedAt(Util.getDateActual());
        product.setPriceUnit(productDTO.getPriceUnit());
        product.setStatus(Status.ACTIVE);
        product.setPriceSell(productDTO.getPriceSell());
        product.setStock(productDTO.getStock());
        return product;
    }

    private ProductDTO mapperDTOProductEntity(@NotNull ProductEntity entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .createdAt(entity.getCreatedAt())
                .name(entity.getName())
                .description(entity.getDescription())
                .priceSell(entity.getPriceSell())
                .categoryId(new CategoryDTO(entity.getCategoryId().getId(), entity.getCategoryId().getDescription(), entity.getCategoryId().getCreatedAt()))
                .status(entity.getStatus())
                .supplierId(new SupplierDTO(entity.getSupplierId().getId(), entity.getSupplierId().getDocument(), entity.getSupplierId().getName()))
                .priceUnit(entity.getPriceUnit())
                .build();
    }


}

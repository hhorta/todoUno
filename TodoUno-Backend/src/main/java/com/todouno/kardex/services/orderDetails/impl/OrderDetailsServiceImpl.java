package com.todouno.kardex.services.orderDetails.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;
import com.todouno.kardex.core.exceptions.service.DataNotFoundServiceException;
import com.todouno.kardex.core.util.Util;
import com.todouno.kardex.domain.dto.CategoryDTO;
import com.todouno.kardex.domain.dto.OrderDetailsDTO;
import com.todouno.kardex.domain.dto.ProductDTO;
import com.todouno.kardex.domain.entity.OrderDetailsEntity;
import com.todouno.kardex.domain.entity.ProductEntity;
import com.todouno.kardex.domain.enums.Status;
import com.todouno.kardex.repository.orderDetails.facade.OrderDetailsRepositoryFacade;
import com.todouno.kardex.repository.products.facade.ProductRepositoryFacade;
import com.todouno.kardex.services.orderDetails.OrderDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepositoryFacade repositoryFacade;
    private final ProductRepositoryFacade productRepositoryFacade;


    OrderDetailsServiceImpl(OrderDetailsRepositoryFacade repositoryFacade, ProductRepositoryFacade productRepositoryFacade) {
        this.repositoryFacade = repositoryFacade;
        this.productRepositoryFacade = productRepositoryFacade;
    }


    @Override
    public List<OrderDetailsDTO> getAllDetails() throws ServiceException {

        List<OrderDetailsEntity> companyList = repositoryFacade.getAllDetails();
        return companyList.stream().map(this::mapperDTODetailsEntity).collect(Collectors.toList());
    }

    @Override
    public OrderDetailsDTO getDetailsById(Integer id) throws ServiceException {
        return mapperDTODetailsEntity(repositoryFacade.getDetailsById(id));
    }

    @Override
    public List<OrderDetailsDTO> getDetailsByCode(String code) throws ServiceException {
        List<OrderDetailsEntity> list = repositoryFacade.getDetailsByCode(code);
        return list.stream().map(this::mapperDTODetailsEntity).collect(Collectors.toList());
    }

    @Override
    public OrderDetailsDTO create(OrderDetailsDTO orderDetailsDTO) throws ServiceException {
        return mapperDTODetailsEntity(repositoryFacade.createDetails(mapperDetailsDTOtoEntity(orderDetailsDTO)));

    }

    @Override
    public String create(List<OrderDetailsDTO> orderDetailsDTOList) throws ServiceException {
        String code = Util.getCode();
        try {
            if (orderDetailsDTOList.isEmpty()) {
                throw new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Orden sin productos");
            } else {
                for (OrderDetailsDTO productOrder : orderDetailsDTOList) {
                    productOrder.setCode(code);
                    repositoryFacade.createDetails(mapperDetailsDTOtoEntity(productOrder));
                }
            }
        } catch (NullPointerException e) {
            throw new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "La orden esta vacia", e);
        }

        return code;

    }


    @Override
    public OrderDetailsDTO update(OrderDetailsDTO orderDetailsDTO) throws ServiceException {
        OrderDetailsEntity details = repositoryFacade.getDetailsById(orderDetailsDTO.getId());
        return mapperDTODetailsEntity(repositoryFacade.updateDetails((details)));
    }


    private OrderDetailsDTO mapperDTODetailsEntity(OrderDetailsEntity entity) {
        return OrderDetailsDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .createAt(entity.getCreatedAt())
                .quantityItem(entity.getQuantity())
                .productsId(new ProductDTO(entity.getProductId().getId(),entity.getProductId().getName(), new CategoryDTO(entity.getProductId().getCategoryId().getId(), entity.getProductId().getCategoryId().getDescription())))
                .build();
    }

    public OrderDetailsEntity mapperDetailsDTOtoEntity(@NotNull OrderDetailsDTO orderDetailsDTO) throws ServiceException {
        OrderDetailsEntity details = new OrderDetailsEntity();
        ProductEntity product = productRepositoryFacade.getProductByCode(orderDetailsDTO.getProductsId().getCode());
        details.setCode(orderDetailsDTO.getCode());
        details.setCreatedAt(Util.getDateActual());
        details.setQuantity(orderDetailsDTO.getQuantityItem());
        details.setProductId(product);
        details.setStatus(Status.ACTIVE);
        details.setDescription(orderDetailsDTO.getDescription());
        details.setSubtotal(orderDetailsDTO.getSubtotal());
        return details;
    }


}

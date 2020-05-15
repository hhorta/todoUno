package com.todouno.kardex.services.supplier.impl;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.SupplierDTO;
import com.todouno.kardex.domain.entity.SupplierEntity;
import com.todouno.kardex.domain.enums.Status;
import com.todouno.kardex.repository.supplier.facade.SupplierRepositoryFacade;
import com.todouno.kardex.services.supplier.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
class SupplierServiceImpl implements SupplierService {

    private final SupplierRepositoryFacade repositoryFacade;

    SupplierServiceImpl(SupplierRepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws ServiceException {

        List<SupplierEntity> companyList = repositoryFacade.getAllSupplier();
        return companyList.stream().map(this::mapperDTOSupplierEntity).collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplierById(Integer id) throws ServiceException {
        return mapperDTOSupplierEntity(repositoryFacade.getSupplierById(id));
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) throws ServiceException {
        return mapperDTOSupplierEntity(repositoryFacade.createSupplier(mapperSupplierDTOtoEntity(supplierDTO)));

    }


    @Override
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO) throws ServiceException {
        SupplierEntity supplier = repositoryFacade.getSupplierByDescription(supplierDTO.getDocument());
        supplier.setDocument(defaultIfNull(supplierDTO.getDocument(), supplier.getDocument()));
        supplier.setAddress(defaultIfNull(supplierDTO.getAddress(), supplier.getAddress()));
        supplier.setDocumentType(defaultIfNull(supplierDTO.getDocumentType(),supplier.getDocumentType()));
        supplier.setEmail(defaultIfNull(supplierDTO.getEmail(),supplier.getEmail()));
        supplier.setName(defaultIfNull(supplierDTO.getName(),supplier.getName()));
        supplier.setPhone(defaultIfNull(supplierDTO.getPhone(),supplier.getPhone()));
        supplier.setStatus(defaultIfNull(supplierDTO.getStatus(),supplier.getStatus()));
        return mapperDTOSupplierEntity(repositoryFacade.updateSupplier((supplier)));
    }

    private SupplierDTO mapperDTOSupplierEntity(SupplierEntity entity) {
        return SupplierDTO.builder()
                .id(entity.getId())
                .document(entity.getDocument())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .build();
    }

    public SupplierEntity mapperSupplierDTOtoEntity(SupplierDTO supplierDTO) {
        SupplierEntity supplier = new SupplierEntity();
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setDocument(supplierDTO.getDocument());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setStatus(Status.ACTIVE);
        return supplier;
    }

}

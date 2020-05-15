package com.todouno.kardex.repository.supplier.facade;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.entity.SupplierEntity;

import java.util.List;

public interface SupplierRepositoryFacade {


    List<SupplierEntity> getAllSupplier() throws ServiceException;

    SupplierEntity getSupplierById(Integer id) throws ServiceException;

    SupplierEntity getSupplierByDescription(String description) throws ServiceException;

    SupplierEntity createSupplier(SupplierEntity entity) throws ServiceException;

    SupplierEntity updateSupplier(SupplierEntity entity) throws ServiceException;

    void deleteSupplier(Integer id) throws ServiceException;
}

package com.todouno.kardex.services.supplier;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    List<SupplierDTO> getAllSupplier() throws ServiceException;

    SupplierDTO getSupplierById(Integer id) throws ServiceException;

    SupplierDTO createSupplier(SupplierDTO supplierDTO) throws ServiceException;

    SupplierDTO updateSupplier(SupplierDTO supplierDTO) throws ServiceException;

}

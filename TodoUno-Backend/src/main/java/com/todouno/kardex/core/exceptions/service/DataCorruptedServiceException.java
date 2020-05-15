package com.todouno.kardex.core.exceptions.service;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;

public class DataCorruptedServiceException extends ServiceException {

    public DataCorruptedServiceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataCorruptedServiceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}

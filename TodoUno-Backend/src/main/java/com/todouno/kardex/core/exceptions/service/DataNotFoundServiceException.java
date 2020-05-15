package com.todouno.kardex.core.exceptions.service;


import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;

public class DataNotFoundServiceException extends ServiceException {

    public DataNotFoundServiceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataNotFoundServiceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}

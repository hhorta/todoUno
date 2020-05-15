package com.todouno.kardex.core.exceptions.persistence;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;

public class DataCorruptedPersistenceException extends ServiceException {

    public DataCorruptedPersistenceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataCorruptedPersistenceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}

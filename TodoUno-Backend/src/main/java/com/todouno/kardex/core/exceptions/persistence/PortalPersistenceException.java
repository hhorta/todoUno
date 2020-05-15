package com.todouno.kardex.core.exceptions.persistence;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.core.exceptions.enums.LogRefServices;

public class PortalPersistenceException extends ServiceException {

    public PortalPersistenceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public PortalPersistenceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}

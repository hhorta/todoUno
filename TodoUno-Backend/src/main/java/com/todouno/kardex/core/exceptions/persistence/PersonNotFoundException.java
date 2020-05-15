package com.todouno.kardex.core.exceptions.persistence;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String x) {
        super(x);
    }
}

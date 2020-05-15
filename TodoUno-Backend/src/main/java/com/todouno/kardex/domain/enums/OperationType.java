package com.todouno.kardex.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OperationType {
    ENTRADA(1),
    SALIDA(2);
    @Getter
    private final Integer id;

}

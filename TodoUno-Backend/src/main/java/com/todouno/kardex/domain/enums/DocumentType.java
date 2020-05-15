package com.todouno.kardex.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DocumentType {
    // identificacion persona natural (cedula)
    CC(1, "Cédula de ciudadanía"),
    // Tarjeta de indentidad
    TI(2, "Terjeta de identidad"),
    // Numero de identificacion tributaria para empresas
    NIT(3 , "NIT"),
    /* pasaporte extranjeria */
    PP(4, "Pasaporte");

    @Getter
    private final Integer id;

    private final String description;

}

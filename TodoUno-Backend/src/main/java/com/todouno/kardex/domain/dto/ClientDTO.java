package com.todouno.kardex.domain.dto;

import com.todouno.kardex.domain.enums.DocumentType;
import com.todouno.kardex.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Info ClientDTO", description = "ClientDTO info")
public class ClientDTO {

    private int id;
    @Schema(example = "1075248593")
    private String document;
    @Schema(example = "Harold")
    private String name;
    @Schema(example = "Horta")
    private String surname;
    @Schema(example = "Calle 45")
    private String address;
    @Schema(example = "31324524534")
    private String phone;
    @Schema(example = "ha@gmail.com")
    private String email;
    @Schema
    private Status status;
    @Schema
    private DocumentType documentType;

    public ClientDTO(Integer id, String document, String name) {
        this.id = id;
        this.document=document;
        this.document= name;
    }
}

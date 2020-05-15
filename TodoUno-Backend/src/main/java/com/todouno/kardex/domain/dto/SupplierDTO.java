package com.todouno.kardex.domain.dto;

import com.todouno.kardex.domain.enums.DocumentType;
import com.todouno.kardex.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Info SupplierDTO", description = "SupplierDto info")
public class SupplierDTO {

    @Schema(example = "1")
    private Integer id;
    @Schema(example = "9001")
    private String document;
    @Schema(example = "Can")
    private String name;
    @Schema(example = "calle 74")
    private String address;
    @Schema(example = "981245")
    private String phone;
    @Schema(example = "can@gmail.com")
    private String email;
    @Schema
    private Status status;
    @Schema
    private DocumentType documentType;

    public SupplierDTO(Integer id, String document, String name) {
        this.id=id;
        this.document=document;
        this.name=name;
    }
}

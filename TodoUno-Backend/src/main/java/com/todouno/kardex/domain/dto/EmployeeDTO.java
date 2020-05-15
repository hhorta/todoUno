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
@Schema(name = "Info EmployeeDTO", description = "EmployeeDTO info")
public class EmployeeDTO {
    private Integer id;
    @Schema(example = "0192")
    private String document;
    @Schema(example = "Juan")
    private String name;
    @Schema(example = "Suarez")
    private String surname;
    @Schema(example = "34831")
    private String phone;
    private Status status;
    private DocumentType documentType;
}

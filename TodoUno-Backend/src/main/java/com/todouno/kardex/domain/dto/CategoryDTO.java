package com.todouno.kardex.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Info CategoryDTO", description = "CategoryDTO info")
public class CategoryDTO {
    @Schema(example = "1")
    private Integer id;
    @Schema(example = "Camisetas")
    private String category;

    private String createdAt;

    public CategoryDTO(Integer id, String category) {
        this.id=id;
        this.category = category;

    }
}

package com.todouno.kardex.domain.dto;

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
@Schema(name = "Info OrderDetailsDTO", description = "OrderDetailsDTO info")
public class OrderDetailsDTO {

    private int id;
    @Schema(example = "234")
    private String code;
    private String createAt;

    @Schema(example = "cualquier cosa")
    private String description;
    @Schema(example = "3")

    private Integer quantityItem;
    @Schema
    private ProductDTO productsId;

    private Status status;
    @Schema
    private Double subtotal;

    public OrderDetailsDTO(Integer id, String code, String createdAt, String description, Integer quantity, ProductDTO productDTO) {
        this.id = id;
        this.code = code;
        this.createAt = createdAt;
        this.description = description;
        this.quantityItem = quantity;
        this.productsId = productDTO;

    }
}

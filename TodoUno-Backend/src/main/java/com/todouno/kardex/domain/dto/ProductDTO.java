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
@Schema(description = "Info ProductDTO", name = "ProductDTO info")
public class ProductDTO {
    @Schema
    protected Integer id;
    @Schema(example = "121")
    protected String code;
    private String createdAt;
    @Schema(example = "Camiseta Spiderman")
    protected String name;
    @Schema(example = "Camiseta hombre araña para niño talla xs")
    private String description;
    @Schema(example = "10000")
    private Double priceUnit;
    @Schema(example = "12000")
    private Double priceSell;
    @Schema(example = "10")
    private Integer stock;

    private Status status;

    private SupplierDTO supplierId;

    private CategoryDTO categoryId;

    public ProductDTO(Integer id) {

    }


    public ProductDTO(Integer id, String name, CategoryDTO categoryDTO) {
        this.id=id;
        this.name=name;
        this.categoryId=categoryDTO;
    }

    public ProductDTO(Integer id, String code, CategoryDTO categoryDTO, Status status) {
        this.id=id;
        this.code=code;
        this.categoryId=categoryDTO;
        this.status= status;
    }

    public ProductDTO(Integer id, String code) {
        this.id=id;
        this.code=code;
    }
}


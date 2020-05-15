package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items implements Serializable {

    private ProductDTO product;
    private Integer quantity;

    public Double getTotal() {
        return product.getPriceSell() * quantity.doubleValue();
    }
}

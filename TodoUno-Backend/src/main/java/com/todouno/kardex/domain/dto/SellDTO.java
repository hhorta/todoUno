package com.todouno.kardex.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "SellDTO", description = "SellDTO info")
public class SellDTO {

    private Integer id;

    private int detailsId;

    private OrderDetailsDTO detailsByDetailsId;
}

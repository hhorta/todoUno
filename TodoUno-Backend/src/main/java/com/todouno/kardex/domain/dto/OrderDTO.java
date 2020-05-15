package com.todouno.kardex.domain.dto;

import com.todouno.kardex.domain.entity.ClientEntity;
import com.todouno.kardex.domain.entity.EmployeeEntity;
import com.todouno.kardex.domain.entity.OrderDetailsEntity;
import com.todouno.kardex.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Info OrderDTO", description = "OrderDTO info")
public class OrderDTO{

    private Integer id;
    private String creatAt;
    private Status status;
    private String numOrderDetails;
    private ClientDTO clientByClientId;
    private EmployeeDTO employeeByEmployeeId;
    private OrderDetailsDTO orderByIdOrder;

}

package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kardex", schema = "todouno", catalog = "")
public class KardexEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "createdAt")
    private String createdAt;
    @Basic
    @Column(name = "OrderNumber")
    private Integer OrderNumber;
    @Basic
    @Column(name = "operation_type")
    private OperationType OperationType;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "balance")
    private Integer balance;
    @Basic
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Basic
    @Column(name = "voucher_id", nullable = false)
    private Integer voucherId;

}

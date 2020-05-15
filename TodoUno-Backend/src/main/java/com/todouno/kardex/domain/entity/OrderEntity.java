package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "todouno")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "creatAt")
    private String creatAt;
    @Basic
    @Column(name = "status")
    private Status status;
    @Basic
    @Column(name = "num_order")
    private String numOrderDetails;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClientEntity clientByClientId;

    @JoinColumn(name = "employee_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EmployeeEntity employeeByEmployeeId;

    @JoinColumn(name = "order_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrderDetailsEntity orderByIdOrder;

    @OneToMany( mappedBy = "orderId")
    private Collection<VoucherEntity> voucherByIdOrder;




}

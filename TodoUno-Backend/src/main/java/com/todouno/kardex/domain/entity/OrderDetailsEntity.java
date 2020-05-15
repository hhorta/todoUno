package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details", schema = "todouno")
public class OrderDetailsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "description", length = 40)
    private String description;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "create_at", length = 20)
    private String createdAt;
    @Basic
    @Column(name = "subtotal")
    private Double subtotal;
    @Basic
    @Column(name = "status")
    private Status status;


    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductEntity productId;

    @OneToMany(mappedBy = "orderByIdOrder")
    private Collection<OrderEntity> orderByIdOrderDetails;
}

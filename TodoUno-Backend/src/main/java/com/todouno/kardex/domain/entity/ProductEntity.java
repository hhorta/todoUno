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
@Table(name = "product", schema = "todouno")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name", length = 80)
    private String name;
    @Basic
    @Column(name = "description", length = 80)
    private String description;

    private String code;
    @Basic
    @Column(name = "stock")
    private Integer stock;
    @Basic
    @Column(name = "createdAt")
    private String createdAt;
    @Basic
    @Column(name = "status")
    private Status status;

    @Basic
    @Column(name = "price_unit")
    private Double priceUnit;
    @Basic
    @Column(name = "price_sell")
    private Double priceSell;

    @OneToMany(mappedBy = "productId")
    private Collection<OrderDetailsEntity> OrderDetailsByIdProduct;

    @JoinColumn(name = "category_id")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private CategoryEntity CategoryId;
    @JoinColumn(name = "supplier_id")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private SupplierEntity supplierId;

}

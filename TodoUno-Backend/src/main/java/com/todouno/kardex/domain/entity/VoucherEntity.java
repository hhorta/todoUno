package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher", schema = "todouno")
public class VoucherEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "total")
    private Integer total;
    @Basic
    @Column(name = "status", length = 1)
    private Status status;

    @JoinColumn(name = "order_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderEntity orderId;

    @OneToMany(mappedBy = "voucherId")
    private Collection<VoucherDetailsEntity> voucherDetailsById;

}

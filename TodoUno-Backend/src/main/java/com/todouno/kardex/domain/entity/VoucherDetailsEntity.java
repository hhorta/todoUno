package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher_details", schema = "todouno", catalog = "")
public class VoucherDetailsEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Status status;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", referencedColumnName = "id", nullable = false)
    private VoucherEntity voucherId;

}

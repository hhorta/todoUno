package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.DocumentType;
import com.todouno.kardex.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier", schema = "todouno", catalog = "")
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "document", length = 15)
    private String document;
    @Basic
    @Column(name = "name", length = 80)
    private String name;
    @Basic
    @Column(name = "address", length = 80)
    private String address;
    @Basic
    @Column(name = "phone", length = 15)
    private String phone;
    @Basic
    @Column(name = "email", length = 45)
    @Email(message = "email no valid")
    private String email;
    @Column(name = "status", length = 15)
    private Status status;
    @Column(name = "document_type", length = 15)
    private DocumentType documentType;
    @OneToMany(mappedBy = "supplierId")
    private Collection<ProductEntity> productsById;


}

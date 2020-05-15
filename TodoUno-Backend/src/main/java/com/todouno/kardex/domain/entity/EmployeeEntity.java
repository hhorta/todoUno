package com.todouno.kardex.domain.entity;

import com.todouno.kardex.domain.enums.DocumentType;
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
@Table(name = "employee", schema = "todouno")
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "document", length = 8)
    private String document;
    @Basic
    @Column(name = "name", length = 45)
    private String name;
    @Basic
    @Column(name = "surname", length = 45)
    private String surname;
    @Basic
    @Column(name = "phone", length = 10)
    private String phone;
    @Basic
    @Column(name = "status", length = 1)
    private Status status;
    private DocumentType documentType;
    @OneToMany(mappedBy = "employeeByEmployeeId")
    private Collection<OrderEntity> orderByIdEmployee;

}

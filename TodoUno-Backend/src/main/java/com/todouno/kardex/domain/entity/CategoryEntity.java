package com.todouno.kardex.domain.entity;

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
@Table(name = "category", schema = "todouno", catalog = "")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "description",length = 80)
    private String description;
    @Basic
    @Column(name = "create_at",length = 20)
    private String createdAt;
    @OneToMany(mappedBy = "CategoryId")
    private Collection<ProductEntity> productById;


}
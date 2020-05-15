package com.todouno.kardex.repository.products;

import com.todouno.kardex.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    @Query(value = "SELECT C FROM ProductEntity C WHERE C.id=:ID")
    ProductEntity findProductEntitiesById (@Param("ID") Integer id );

    @Query(value = "SELECT C FROM ProductEntity C WHERE C.description=:DESCRIPTION")
    ProductEntity findProductEntitiesByDescription (@Param("DESCRIPTION") String description);

    @Query("SELECT C FROM ProductEntity C WHERE C.code=:CODE")
    ProductEntity findProductEntitiesByCode (@Param("CODE") String code);
}

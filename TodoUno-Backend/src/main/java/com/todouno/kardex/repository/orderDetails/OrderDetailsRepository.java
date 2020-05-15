package com.todouno.kardex.repository.orderDetails;

import com.todouno.kardex.domain.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity,Integer> {

    @Query("SELECT C FROM OrderDetailsEntity C WHERE C.id=:ID")
    OrderDetailsEntity findDetailsEntitiesById (@Param("ID") Integer id );

    @Query("SELECT C FROM OrderDetailsEntity C WHERE C.code=:CODE")
    OrderDetailsEntity findDetailsEntitiesByCode (@Param("CODE") String code);

    @Query("SELECT C FROM OrderDetailsEntity C WHERE C.code=:CODE")
    List<OrderDetailsEntity> getDetailsByCode (@Param("CODE") String code);
}

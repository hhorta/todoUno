package com.todouno.kardex.repository.order;

import com.todouno.kardex.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("select o from OrderEntity o inner join o.orderByIdOrder ord inner join ord.productId where o.id=:ID")
    OrderEntity findOrderEntitiesById (@Param("ID") Integer id);


    @Query(value="select * from orders o inner join order_details od on o.num_order= od.code inner join product p on od.product_id = p.id where o.id=?", nativeQuery = true)
    List<OrderEntity> findOrderListById (Integer id);
}

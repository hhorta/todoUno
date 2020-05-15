package com.todouno.kardex.repository.supplier;

import com.todouno.kardex.domain.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer> {

    @Query("SELECT C FROM SupplierEntity C WHERE C.id=:ID")
    SupplierEntity findSupplierEntitiesById (@Param("ID") Integer id );

    @Query("SELECT C FROM SupplierEntity C WHERE C.document=:NIT")
    SupplierEntity findSupplierEntitiesByDescription (@Param("NIT") String description);
}

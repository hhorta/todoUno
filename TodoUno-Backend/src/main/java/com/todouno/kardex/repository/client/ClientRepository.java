package com.todouno.kardex.repository.client;

import com.todouno.kardex.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Integer> {

    @Query("SELECT C FROM ClientEntity C WHERE C.id=:ID")
    ClientEntity findClientEntitiesById (@Param("ID") Integer id );

    @Query("SELECT C FROM ClientEntity C WHERE C.document=:DOCUMENT")
    ClientEntity findClientEntitiesByDocument (@Param("DOCUMENT") String document);

    @Query("SELECT C FROM ClientEntity C WHERE C.document like concat('%' ,:DOCUMENT,'%')")
    List<ClientEntity> findClientByDocument(@Param("DOCUMENT") String document);
}

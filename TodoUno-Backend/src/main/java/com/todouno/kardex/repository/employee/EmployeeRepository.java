package com.todouno.kardex.repository.employee;

import com.todouno.kardex.domain.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface    EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {

    @Query("SELECT C FROM EmployeeEntity C WHERE C.id=:ID")
    EmployeeEntity findEmployeeEntitiesById (@Param("ID") Integer id );

    @Query("SELECT C FROM EmployeeEntity C WHERE C.document=:DOCUMENT")
    EmployeeEntity findEmployeeEntitiesByDocument (@Param("DOCUMENT") String document);

}

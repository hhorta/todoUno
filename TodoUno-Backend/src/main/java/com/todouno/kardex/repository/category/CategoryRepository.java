package com.todouno.kardex.repository.category;

import com.todouno.kardex.domain.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {

    @Query("SELECT C FROM CategoryEntity C WHERE C.id=:ID")
    CategoryEntity findCategoryEntitiesById (@Param("ID") Integer id );

    @Query("SELECT C FROM CategoryEntity C WHERE C.description=:DESCRIPTION")
    CategoryEntity findCategoryEntitiesByDescription (@Param("DESCRIPTION") String description);
}

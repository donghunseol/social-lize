package com.example.project.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.socialId.id = :socialId and c.socialId.status = 'ACTIVE'")
    List<Category> findBySocialId(@Param("socialId") Integer socialId);
}

package com.example.project.category_name;

import com.example.project._core.enums.DeleteStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryNameRepository extends JpaRepository<CategoryName, Integer> {
    @Query("select c from CategoryName c where c.name = :name")
    Optional<CategoryName> findByName(@Param("name") String name);

    @Query("select c from CategoryName c where c.status = :status")
    List<CategoryName> findAllByStatus(@Param("status") DeleteStateEnum status);
}

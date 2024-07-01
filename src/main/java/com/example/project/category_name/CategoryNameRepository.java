package com.example.project.category_name;

import com.example.project._core.enums.DeleteStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryNameRepository extends JpaRepository<CategoryName, Integer> {

    // 카테고리 이름으로 카테고리 조회 (관리자)
    @Query("select c from CategoryName c where c.name = :name")
    Optional<CategoryName> findByName(@Param("name") String name);

    // 카테고리 전체 리스트 조회 (관리자)
    @Query("select c from CategoryName c where c.status = :status ORDER BY c.id DESC")
    List<CategoryName> findAllByStatus(@Param("status") DeleteStateEnum status);

    // 카테고리 전체 갯수 조회 (관리자)
    @Query("select count(*) from CategoryName cn where cn.status = 'ACTIVE'")
    Integer findAllByCategoryNameStatus();

    @Query("select c from CategoryName c where c.name = :name")
    CategoryName findNameByCategoryName(@Param("name") String name);
}

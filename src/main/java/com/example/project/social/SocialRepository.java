package com.example.project.social;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Integer> {

    // 활동 소셜 리스트 조회 (관리자)
    @Query("select s from Social s where s.name = :name")
    Optional<Social> findByName(@Param("name") String name);

    // 활동 소셜 갯수 조회 (관리자)
    @Query("select count(*) from Social s where s.status = 'ACTIVE'")
    Integer findAllActiveSocial();
}

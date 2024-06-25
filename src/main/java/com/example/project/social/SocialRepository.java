package com.example.project.social;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Integer> {
    @Query("select s from Social s where s.name = :name")
    Optional<Social> findByName(@Param("name") String name);


}

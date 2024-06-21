package com.example.project.rereply;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RereplyRepository extends JpaRepository<Rereply, Integer> {


}

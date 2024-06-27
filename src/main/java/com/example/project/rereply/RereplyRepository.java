package com.example.project.rereply;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RereplyRepository extends JpaRepository<Rereply, Integer> {

    @Query("select r from Rereply r where r.replyId.id = :replyId")
    List<Rereply> findByReplyId(@Param("replyId") Integer replyId);
}

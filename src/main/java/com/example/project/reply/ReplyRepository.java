package com.example.project.reply;

import com.example.project._core.enums.ReplyEnum;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query(value = "SELECT COUNT(r.id) + COUNT(rr.id) AS replycount " +
            "FROM reply_tb r " +
            "LEFT JOIN rereply_tb rr ON r.id = rr.reply_id " +
            "WHERE r.board_id = :boardId", nativeQuery = true)
    Integer replyCount(@Param("boardId") int boardId);

    @Query("SELECT r FROM Reply r WHERE r.boardId.id = :boardId and r.state = 'ACTIVE'")
    List<Reply> findByReply(@Param("boardId") Integer boardId);

    @Query("select r from Reply r where r.id = :replyId and r.state = 'ACTIVE'")
    Reply findByActive(@Param("replyId") Integer replyId);
}

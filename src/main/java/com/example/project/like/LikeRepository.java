package com.example.project.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    // 게시물(boardId)에 대한 좋아요 개수 조회
    @Query("select count(l) from Like l where l.boardId.id = :boardId")
    Integer findByLikeCount(@Param("boardId") Integer boardId);
}

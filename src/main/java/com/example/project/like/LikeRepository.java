package com.example.project.like;

import com.example.project.board.Board;
import com.example.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    // 게시물(boardId)에 대한 좋아요 개수 조회
    @Query("select count(l) from Like l where l.boardId.id = :boardId and l.boardId.state = 'ACTIVE'")
    Integer findByLikeCount(@Param("boardId") Integer boardId);

    @Query("select count(l) from Like l where l.boardId.id = :boardId and l.userId.id = :userId and l.boardId.state = 'ACTIVE'")
    Integer findByLikeUserId(@Param("boardId") Integer boardId, @Param("userId") Integer userId);

    @Query("select l from Like l where l.userId.id = :userId and l.boardId.id = :boardId and l.boardId.state = 'ACTIVE'")
    Optional<Like> findByUserAndBoard(@Param("userId") Integer userId, @Param("boardId") Integer boardId);
}

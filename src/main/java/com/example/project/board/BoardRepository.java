package com.example.project.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b where b.socialId.id = :socialId")
    List<Board> findByBoardSocialId(@Param("socialId") Integer socialId);
}

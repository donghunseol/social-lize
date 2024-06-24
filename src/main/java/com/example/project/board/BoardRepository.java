package com.example.project.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b where b.socialId.id = :socialId order by b.id desc")
    List<Board> findByBoardSocialId(@Param("socialId") Integer socialId);

    @Query(value = "select b.* from board_tb b JOIN bookmark_tb bm ON b.id = bm.board_id WHERE bm.user_id = :userId", nativeQuery = true)
    List<Board> findByBoards(@Param("userId") Integer userId);
}

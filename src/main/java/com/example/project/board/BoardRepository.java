package com.example.project.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b where b.socialId.id = :socialId order by b.id desc")
    List<Board> findByBoardSocialId(@Param("socialId") Integer socialId);

    @Query(value = "select b.* from board_tb b JOIN bookmark_tb bm ON b.id = bm.board_id WHERE bm.user_id = :userId", nativeQuery = true)
    List<Board> findByBoards(@Param("userId") Integer userId);

    // 유저가 작성한 전체 게시글 조회 (관리자)
    @Query("SELECT new com.example.project.board.BoardResponse$BoardList(b.id, s.name, u.nickname, b.content, b.createdAt)" +
            "FROM Board b " +
            "JOIN b.socialId s " +
            "JOIN b.userId u " +
            "WHERE u.role = 'USER'")
    List<BoardResponse.BoardList> findAllBoardList();

    // 작성된 게시글 상세 조회 (관리자)
    @Query("SELECT b FROM Board b JOIN b.socialId s JOIN b.userId u WHERE b.id = :boardId")
    Optional<Board> findByBoardId(@Param("boardId") Integer boardId);
}

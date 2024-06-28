package com.example.project.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b where b.socialId.id = :socialId order by b.id desc")
    List<Board> findByBoardSocialId(@Param("socialId") Integer socialId);

    //특정 소셜에 내가 작성 한 글 갯수
    @Query("select count(*) from Board b where b.socialId.id = :socialId and b.userId.id = :userId")
    Integer getArticleCountByBoardSocialIdAndUserId(Integer userId, Integer socialId);

    @Query(value = "select b.* from board_tb b JOIN bookmark_tb bm ON b.id = bm.board_id WHERE bm.user_id = :userId", nativeQuery = true)
    List<Board> findByBoards(@Param("userId") Integer userId);

    // 유저가 작성한 전체 게시글 조회 (관리자)
    @Query("SELECT b FROM Board b JOIN b.socialId s JOIN b.userId u WHERE u.role = 'USER'")
    List<Board> findAllBoardList();

    // 작성된 게시글 상세 조회 (관리자)
    @Query("SELECT b FROM Board b JOIN b.socialId s JOIN b.userId u WHERE b.id = :boardId")
    Optional<Board> findByBoardId(@Param("boardId") Integer boardId);

    @Query("SELECT FUNCTION('DAYNAME', b.createdAt) as dayOfWeek, COUNT(b) as count " +
            "FROM Board b " +
            "WHERE b.socialId.id = :socialId AND b.role = 'POST' " +
            "GROUP BY FUNCTION('DAYNAME', b.createdAt) " +
            "ORDER BY count DESC")
    List<Object[]> findPostCountsByDayOfWeek(@Param("socialId") Integer socialId);

    // 유저가 작성한 게시글 갯수 (관리자)
    @Query("select count(*) from Board b where b.role = 'POST'")
    Integer findByBoardRole();
}

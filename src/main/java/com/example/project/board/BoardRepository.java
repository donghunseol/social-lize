package com.example.project.board;

import com.example.project.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b where b.socialId.id = :socialId and b.state = 'ACTIVE' order by b.id desc")
    List<Board> findByBoardSocialId(@Param("socialId") Integer socialId);

    //특정 소셜에 내가 작성 한 글 갯수
    @Query("select count(*) from Board b where b.socialId.id = :socialId and b.userId.id = :userId and b.state = 'ACTIVE'")
    Integer getArticleCountByBoardSocialIdAndUserId(Integer userId, Integer socialId);

    @Query(value = "select b.* from board_tb b JOIN bookmark_tb bm ON b.id = bm.board_id WHERE bm.user_id = :userId and b.state = 'ACTIVE' order by b.id desc", nativeQuery = true)
    List<Board> findByBoards(@Param("userId") Integer userId);

    // 유저가 작성한 전체 게시글 조회 (관리자)
    @Query("SELECT b FROM Board b JOIN b.socialId s JOIN b.userId u WHERE u.role = 'USER' ORDER BY b.id DESC")
    List<Board> findAllBoardList();

    // 작성된 게시글 상세 조회 (관리자)
    @Query("SELECT b FROM Board b JOIN b.socialId s JOIN b.userId u WHERE b.id = :boardId")
    Optional<Board> findByBoardId(@Param("boardId") Integer boardId);

    @Query("SELECT FUNCTION('DAYNAME', b.createdAt) as dayOfWeek, COUNT(b) as count " +
            "FROM Board b " +
            "WHERE b.socialId.id = :socialId AND b.role = 'POST' and b.state = 'ACTIVE'" +
            "GROUP BY FUNCTION('DAYNAME', b.createdAt) " +
            "ORDER BY count DESC")
    List<Object[]> findPostCountsByDayOfWeek(@Param("socialId") Integer socialId);

    // 유저가 작성한 게시글 갯수 (관리자)
    @Query("select count(*) from Board b where b.role = 'POST'")
    Integer findByBoardRole();

    @Query("select b from Board b where b.userId.id = :userId and b.state = 'ACTIVE' order by b.id desc")
    List<Board> findAllUserId(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT b FROM Board b " +
            "LEFT JOIN FETCH b.replies r " +
            "WHERE r.userId.id = :userId and b.state = 'ACTIVE'")
    List<Board> findBoardsByUserReplies(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT b FROM Board b " +
            "JOIN b.replies r " +
            "JOIN r.rereplies rr " +
            "WHERE rr.userId.id = :userId and b.state = 'ACTIVE' order by b.id desc")
    List<Board> findRepliesByUserRereplies(@Param("userId") Integer userId);

    @Query("select b from Board b where b.id = :boardId and b.userId.id = :userId")
    Optional<Board> findByBoardIdAndUserId(@Param("boardId") Integer boardId, @Param("userId") Integer userId);
}

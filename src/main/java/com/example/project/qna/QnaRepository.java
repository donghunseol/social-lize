package com.example.project.qna;

import com.example.project._core.enums.QnaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QnaRepository extends JpaRepository<Qna, Integer> {

    @Query("select q from Qna q where q.id = :qnaId and q.state = :state")
    Qna findByQnaIdAndWaiting(@Param("qnaId") Integer qnaId, @Param("state") QnaEnum qnaEnum);

    @Query("select q from Qna q where q.id = :qnaId")
    Qna findByQnaId(@Param("qnaId") Integer qnaId);

    @Query("select q from Qna q where q.userId.id = :userId and q.state != 'DELETE'")
    List<Qna> findByUserId(@Param("userId") Integer userId);

    // 유저가 작성한 문의 리스트 조회 (관리자)
    @Query("select q from Qna q join q.userId u where q.state = 'ANSWER' or q.state = 'WAITING'")
    List<Qna> findAllQnaList();

    // 유저가 작성한 문의 갯수 (관리자)
    @Query("select count(*) from Qna q")
    Integer findAllQnaCount();

    // 문의 상세 조회 (관리자)
    @Query("select q from Qna q join q.userId u where q.id = :qnaId")
    Optional<Qna> findQnaAndUserByQnaId(@Param("qnaId") Integer qnaId);
}

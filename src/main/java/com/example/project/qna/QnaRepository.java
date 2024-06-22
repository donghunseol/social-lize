package com.example.project.qna;

import com.example.project._core.enums.QnaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Integer> {

    @Query("select q from Qna q where q.id = :qnaId and q.state = :state")
    Qna findByQnaIdAndWaiting(@Param("qnaId") Integer qnaId, @Param("state") QnaEnum qnaEnum);

    @Query("select q from Qna q where q.id = :qnaId")
    Qna findByQnaId(@Param("qnaId") Integer qnaId);

    @Query("select q from Qna q where q.userId.id = :userId and q.state != 'DELETE'")
    List<Qna> findByUserId(@Param("userId") Integer userId);
}

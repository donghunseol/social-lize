package com.example.project.notice;

import com.example.project._core.enums.DeleteStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    // 등록된 공지 갯수 (관리자)
    @Query("SELECT COUNT(n) FROM Notice n WHERE n.state = :state")
    Integer findAllNoticeCountByState(@Param("state") DeleteStateEnum state);

    // 등록된 공지 목록 조회(관리자)
    @Query("SELECT n FROM Notice n WHERE n.state = :state ORDER BY n.id DESC")
    List<Notice> findAllByState(@Param("state") DeleteStateEnum state);

    // 공지 상세 조회 (관리자)
    @Query("select n from Notice n join n.board b where n.id = :noticeId")
    Optional<Notice> findByNoticeId(@Param("noticeId") Integer noticeId);
}

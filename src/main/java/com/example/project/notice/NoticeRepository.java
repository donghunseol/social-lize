package com.example.project.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    // 공지 갯수 (관리자)
    @Query("select count(*) from Notice n")
    Integer findAllNoticeCount();

    // 공지 상세 조회 (관리자)
    @Query("select n from Notice n join n.board b where n.id = :noticeId")
    Optional<Notice> findByNoticeId(@Param("noticeId") Integer noticeId);
}

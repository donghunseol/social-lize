package com.example.project.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    // 신고 리스트 갯수 (관리자)
    @Query("SELECT COUNT(r) FROM Report r")
    Integer countAllBy();
}

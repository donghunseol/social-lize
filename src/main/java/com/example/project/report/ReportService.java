package com.example.project.report;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    // 신고 리스트 조회 (관리자)
    public ReportResponse.ReportDTO getReportList() {
        Integer count = reportRepository.countAllBy();
        List<Report> reports = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ReportResponse.ReportDTO.ReportList> reportList = reports.stream()
                .map(ReportResponse.ReportDTO.ReportList::new)
                .collect(Collectors.toList());

        return new ReportResponse.ReportDTO(count, reportList);
    }
}

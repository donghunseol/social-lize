package com.example.project.report;

import com.example.project._core.enums.DeleteStateEnum;
import com.example.project._core.enums.ReportResultEnum;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final BoardRepository boardRepository;

    // 신고 리스트 조회 (관리자)
    public ReportResponse.ReportDTO getReportList() {
        Integer count = reportRepository.countAllBy();
        List<Report> reports = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ReportResponse.ReportDTO.ReportList> reportList = reports.stream()
                .map(ReportResponse.ReportDTO.ReportList::new)
                .collect(Collectors.toList());

        return new ReportResponse.ReportDTO(count, reportList);
    }

    // 신고 상세 조회 (관리자)
    public ReportResponse.DetailDTO getReportDetail(Integer reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new Exception404("해당 신고를 찾을 수 없습니다."));

        return new ReportResponse.DetailDTO(report);
    }

    // 신고 적합 (관리자)
    public void updateReportStatusApproval(Integer reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("해당 신고를 찾을 수 없습니다."));
        report.setResult(ReportResultEnum.APPROVAL);
        reportRepository.save(report);

        if (report.getResult() == ReportResultEnum.APPROVAL) {
            Board board = boardRepository.findById(report.getBoardId().getId())
                    .orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
            board.setState(DeleteStateEnum.DELETED);
            boardRepository.save(board);
        }
    }

    // 신고 부적합 (관리자)
    public void updateReportStatusRefuse(Integer reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("해당 신고를 찾을 수 없습니다."));
        report.setResult(ReportResultEnum.REFUSE);

        if (report.getResult() == ReportResultEnum.REFUSE) {
            Board board = boardRepository.findById(report.getBoardId().getId())
                    .orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
            board.setState(DeleteStateEnum.ACTIVE);
            boardRepository.save(board);
        }
        reportRepository.save(report);
    }
}

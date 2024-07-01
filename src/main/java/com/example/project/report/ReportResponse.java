package com.example.project.report;

import com.example.project._core.enums.ReportResultEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.board.Board;
import lombok.Data;

import java.util.List;

public class ReportResponse {

    // 신고 DTO
    @Data
    public static class ReportDTO {
        private Integer count;
        private List<ReportList> reportList;

        public ReportDTO(Integer count, List<ReportList> reportList) {
            this.count = count;
            this.reportList = reportList;
        }

        // 신고 리스트 DTO
        @Data
        public static class ReportList {
            private Integer reportId;
            private Integer boardId;
            private String content;
            private String reportNickname;
            private String createdAt;
            private String result;

            public ReportList(Report report) {
                this.reportId = report.getId();
                this.boardId = report.getBoardId().getId();
                this.content = report.getContent();
                this.reportNickname = report.getReportUserId().getNickname();
                this.createdAt = LocalDateTimeFormatter.convert(report.getCreatedAt());
                this.result = report.getResult().getValue();
            }
        }
    }

    // 신고 상세 DTO
    @Data
    public static class DetailDTO {
        private Integer id;
        private String userNickname;
        private ReportBoard reportBoard;
        private String content;
        private String createdAt;

        public DetailDTO(Report report) {
            this.id = report.getId();
            this.userNickname = report.getReportUserId().getNickname();
            this.reportBoard = new ReportBoard(report.getBoardId());
            this.content = report.getContent();
            this.createdAt = LocalDateTimeFormatter.convert(report.getCreatedAt());
        }

        @Data
        public static class ReportBoard {
            private Integer boardId;
            private String userNickname;
            private String content;
            private String createdAt;

            public ReportBoard(Board board) {
                this.boardId = board.getId();
                this.userNickname = board.getUserId().getNickname();
                this.content = board.getContent();
                this.createdAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
            }
        }
    }
}

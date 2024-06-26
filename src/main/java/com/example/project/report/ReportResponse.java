package com.example.project.report;

import com.example.project._core.enums.ReportResultEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.util.List;

public class ReportResponse {

    // 신고 DTO
    @Data
    public static class ReportDTO{
        private Integer count;
        private List<ReportList> reportList;

        public ReportDTO(Integer count, List<ReportList> reportList) {
            this.count = count;
            this.reportList = reportList;
        }

        // 신고 리스트 DTO
        @Data
        public static class ReportList{
            private Integer reportId;
            private Integer boardId;
            private String content;
            private String reportNickname;
            private String createdAt;
            private ReportResultEnum result;

            public ReportList(Report report) {
                this.reportId = report.getId();
                this.boardId = report.getBoardId().getId();
                this.content = report.getContent();
                this.reportNickname = report.getUserId().getNickname();
                this.createdAt = LocalDateTimeFormatter.convert(report.getCreatedAt());
                this.result = report.getResult();
            }
        }
    }
}

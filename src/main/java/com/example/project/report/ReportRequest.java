package com.example.project.report;

import com.example.project._core.enums.ReportResultEnum;
import lombok.Data;

public class ReportRequest {

    @Data
    public static class ProcessDTO {
        private ReportResultEnum result;

        public ProcessDTO(Report report) {
            this.result = report.getResult();
        }
    }
}

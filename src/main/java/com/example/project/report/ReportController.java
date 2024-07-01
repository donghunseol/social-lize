package com.example.project.report;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;
    private final HttpSession session;

    @GetMapping("/report")
    public String detail() {
        return "layout/report";
    }
}

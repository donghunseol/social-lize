package com.example.project.report;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;
    private final HttpSession session;
}

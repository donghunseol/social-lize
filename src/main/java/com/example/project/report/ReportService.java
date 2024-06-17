package com.example.project.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
}

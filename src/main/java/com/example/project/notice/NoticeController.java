package com.example.project.notice;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;
    private final HttpSession session;
}

package com.example.project.notice;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class NoticeController {
    private final NoticeService noticeService;
    private final HttpSession session;
}

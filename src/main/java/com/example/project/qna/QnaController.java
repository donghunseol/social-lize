package com.example.project.qna;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QnaController {
    private final QnaService qnaService;
    private final HttpSession session;
}

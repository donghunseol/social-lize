package com.example.project.rereply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RereplyController {
    private final RereplyService rereplyService;
    private final HttpSession session;
}

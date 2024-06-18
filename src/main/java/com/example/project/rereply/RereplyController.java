package com.example.project.rereply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class RereplyController {
    private final RereplyService rereplyService;
    private final HttpSession session;
}

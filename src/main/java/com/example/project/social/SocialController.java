package com.example.project.social;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SocialController {
    private final SocialService socialService;
    private final HttpSession session;

    @GetMapping("/social/notJoined")
    public String socialNotJoin() {
        return "social/notJoinedForm";
    }
}

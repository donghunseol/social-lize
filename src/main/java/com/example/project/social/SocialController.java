package com.example.project.social;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialController {
    private final SocialService socialService;
    private final HttpSession session;
}

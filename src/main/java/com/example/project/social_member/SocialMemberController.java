package com.example.project.social_member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialMemberController {
    private final SocialMemberService socialMemberService;
    private final HttpSession session;
}

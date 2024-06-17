package com.example.project.social_member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class SocialMemberController {
    private final SocialMemberService socialMemberService;
    private final HttpSession session;

    @GetMapping("/member/invite")
    public String member() {

        return "member/member_invite";
    }
}

package com.example.project.social_member;

import com.example.project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialMemberRestController {
    private final SocialMemberService socialMemberService;
    private final HttpSession session;

    // 소셜 가입 신청
    @PostMapping("/social-members/apply/{socialId}")
    public ResponseEntity<?> applySocialMember(@PathVariable Integer socialId) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialMemberService.applySocialMember(socialId, 5);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

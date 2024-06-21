package com.example.project.social_member;

import com.example.project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SocialMemberRestController {
    private final SocialMemberService socialMemberService;
    private final HttpSession session;

    // 소셜 가입 신청
    @PostMapping("/{socialId}/social-members/apply")
    public ResponseEntity<?> applySocialMember(@PathVariable Integer socialId) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialMemberService.applySocialMember(socialId, 5);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 소셜 가입 승인, 거절
    @PutMapping("/social-members/{socialMemberId}/state")
    public ResponseEntity<?> updateSocialMemberState(@PathVariable Integer socialMemberId, @RequestBody SocialMemberRequest.UpdateStateDTO newState) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialMemberService.updateSocialMemberState(2, socialMemberId, newState);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

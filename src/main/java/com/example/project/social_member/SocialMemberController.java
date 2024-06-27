package com.example.project.social_member;

import ch.qos.logback.core.model.Model;
import com.example.project._core.utils.ApiUtil;
import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SocialMemberController {
    private final SocialMemberService socialMemberService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 소셜 멤버 리스트
//    @GetMapping("/member/invite/{socialId}")
//    public String member(@PathVariable("socialId") Integer socialId, HttpServletRequest request) {
//        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
//        SocialMemberResponse.MemberDTO modal = socialMemberService.member(sessionUser.getId(), socialId);
//        request.setAttribute("modal", modal);
//
//        return "member/memberInvite";
//    }

    // 소셜 가입 신청
    @PostMapping("/{socialId}/social-members/apply")
    public String applySocialMember(@PathVariable Integer socialId) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        socialMemberService.applySocialMember(socialId, sessionUser.getId());
        return "redirect:/social/detail/" + socialId;
    }

    // 소셜 멤버 리스트 조회
    @GetMapping("/social/{socialId}/member-list")
    public ResponseEntity<?> socialMemberList(@PathVariable Integer socialId) {
        List<SocialMemberResponse.SocialMemberList> socialMemberList = socialMemberService.getSocialMembersBySocialId(socialId);
        return ResponseEntity.ok(new ApiUtil<>(socialMemberList));
    }
}

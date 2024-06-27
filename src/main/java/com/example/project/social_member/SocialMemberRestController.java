package com.example.project.social_member;

import com.example.project._core.utils.ApiUtil;
import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SocialMemberRestController {
    private final SocialMemberService socialMemberService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 소셜 가입 승인, 거절
    @PutMapping("/social-members/state")
    public ResponseEntity<?> updateSocialMemberState(@RequestParam("socialId") Integer socialId,
                                                     @RequestParam("userId") Integer userId,
                                                     @RequestParam("isApproved") String isApproved) {

        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();

        socialMemberService.updateSocialMemberState(userId, sessionUser.getId(), socialId, isApproved);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

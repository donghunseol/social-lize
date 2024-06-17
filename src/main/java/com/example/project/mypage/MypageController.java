package com.example.project.mypage;

import com.example.project.social.SocialService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MypageController {
    private final SocialService socialService;
    private final HttpSession session;

    // 북마크 페이지
    @GetMapping("/mypage/bookmark")
    public String bookmark() {
        return "/mypage/bookmarkForm";
    }

    // 가입신청 현황 페이지
    @GetMapping("/mypage/joinstatus")
    public String joinstatus() {
        return "/mypage/joinstatusForm";
    }
}

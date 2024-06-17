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

    // 가입하지 않은 소셜 둘러보기 페이지
    @GetMapping("/social/notJoined")
    public String socialNotJoin() {
        return "social/notJoinedForm";
    }

    // 새 소셜 추가하기 페이지
    @GetMapping("/social/socialAdd")
    public String socialAdd() {
        return "social/socialaddForm";
    }

    // 서랍 페이지
    @GetMapping("/social/fileadd")
    public String fileAdd() {
        return "social/fileaddForm";
    }

}

package com.example.project.social;

import com.example.project.file.FileRequest;
import com.example.project.file.FileService;
import com.example.project.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class SocialController {
    private final SocialService socialService;
    private final FileService fileService;
    private final HttpSession session;

    @GetMapping("/social/detail/{socialId}")
    public String socialDetail(@PathVariable int socialId) {

        return "social/detail";
    }

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

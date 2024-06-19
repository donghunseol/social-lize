package com.example.project.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/test1")
    public String socialAddForm(){
        return "/member/joinForm";
    }
    @GetMapping("/test2")
    public String socialAdddForm(){
        return "/member/joinMain";
    }

    @GetMapping("/")
    public String mainPage(Integer categoryNameId, HttpServletRequest request) {
        Integer userId = 1;
        UserResponse.MainDTO model = userService.mainPage(userId, categoryNameId);
        request.setAttribute("model", model);

        return "main";
    }

    // 웹 시작화면 페이지
    @GetMapping("/user/notloggedinmain")
    public String socialAdd() {
        return "social/notLoggedinMain";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }
}

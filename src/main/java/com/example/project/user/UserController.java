package com.example.project.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String mainPage(HttpServletRequest request) {
        Integer userId = 1;
        UserResponse.MainDTO model = userService.mainPage(userId);
        request.setAttribute("model", model);

        return "main";
    }
}

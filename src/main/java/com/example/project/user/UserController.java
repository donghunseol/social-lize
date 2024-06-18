package com.example.project.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/test")
    public String socialAddForm(){
        return "/member/memberInvite";
    }

    @GetMapping("/")
    public String main(){
        return "main";
    }
}

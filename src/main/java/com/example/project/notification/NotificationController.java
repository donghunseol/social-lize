package com.example.project.notification;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class NotificationController {
    private final NotificationService notificationService;
    private final HttpSession session;

    //회원가입 페이지 - 자체가입
    @GetMapping("/notiTest")
    public String notiTest(){
        return "notificationTest/test";
    }
}

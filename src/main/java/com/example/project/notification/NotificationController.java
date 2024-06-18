package com.example.project.notification;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class NotificationController {
    private final NotificationService notificationService;
    private final HttpSession session;
}

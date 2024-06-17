package com.example.project.notification;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationController {
    private final NotificationService notificationService;
    private final HttpSession session;
}

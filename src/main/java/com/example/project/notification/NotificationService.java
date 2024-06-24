package com.example.project.notification;

import com.example.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public int getUnCheckedCountByUserId(Integer userid){
        return notificationRepository.getUnCheckedCountByUserId(userid);
    }
}

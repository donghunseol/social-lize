package com.example.project.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public int getUnCheckedCountByUserId(Integer userid){
        return notificationRepository.getUnCheckedCountByUserId(userid);
    }

    public NotificationResponse.ListDTO getAllByUserId(Integer userid){
        List<Notification> notificationList = notificationRepository.getAllByUserId(userid);
        return new NotificationResponse.ListDTO(notificationList);
    }
}

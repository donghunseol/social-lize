package com.example.project.notification;

import com.example.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("select count(n) from Notification n where n.checked = false and n.userId.id = :userId")
//    @Query("select count(n) from Notification n where n.checked = false and n.userId = :user")
    Integer getUnCheckedCountByUserId(Integer userId);
//    Integer getCountUnCheckedByUserId(@Param() Integer userId);
}

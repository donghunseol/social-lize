package com.example.project.notification;

import com.example.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("select count(n) from Notification n where n.checked = false and n.userId.id = :userId")
    Integer getUnCheckedCountByUserId(Integer userId);

    @Query("select n from Notification n where n.userId.id = :userId")
    List<Notification> getAllByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.checked = true WHERE n.userId.id = :userId")
    void updateAllCheckedById(Integer userId);
}

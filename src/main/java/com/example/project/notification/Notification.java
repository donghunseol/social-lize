package com.example.project.notification;

import com.example.project._core.enums.NotificationEnum;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "notification_tb")
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 알림 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationEnum role; // 알람 종류 구별

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Column(nullable = false)
    private Boolean checked; // 알림 확인 여부

    @Builder
    public Notification(Integer id, User userId, NotificationEnum role, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.role = role;
        this.createdAt = createdAt;
    }
}



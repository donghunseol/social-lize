package com.example.project.notification;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "notification_tb")
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 알림 번호

    @Builder
    public Notification(Integer id) {
        this.id = id;

    }
}



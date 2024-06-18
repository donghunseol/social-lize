package com.example.project.chat;

import com.example.project.social.Social;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "chat_tb")
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 채팅 번호

    @JoinColumn(name = "socialId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Social socialId; // 소셜 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @Column(nullable = false)
    private String comment; // 채팅 내용

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Chat(Integer id, Social socialId, User userId, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.socialId = socialId;
        this.userId = userId;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}



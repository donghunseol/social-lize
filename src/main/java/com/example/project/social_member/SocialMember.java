package com.example.project.social_member;

import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.SocialMemberStateEnum;
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
@Table(name = "social_member_tb")
@Entity
public class SocialMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 소셜 멤버 번호

    @JoinColumn(name = "socialId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Social socialId; // 소셜 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialMemberRoleEnum role; // 소셜 권한

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialMemberStateEnum state; // 가입 상태

    @CreationTimestamp
    private LocalDateTime createdAt; // 가입 일자

    @Builder

    public SocialMember(Integer id, Social socialId, User userId, SocialMemberRoleEnum role, SocialMemberStateEnum state, LocalDateTime createdAt) {
        this.id = id;
        this.socialId = socialId;
        this.userId = userId;
        this.role = role;
        this.state = state;
        this.createdAt = createdAt;
    }
}



package com.example.project.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 유저 번호

    @Column(nullable = false)
    private String username; // 로그인 아이디

    @Column(nullable = false, unique = true)
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}",
            message = "올바르지 않은 이메일 형식입니다.")
    private String email; // 이메일

    @Column(nullable = false)
    private String password; // 비밀번호

    @CreationTimestamp
    private LocalDateTime createdAt; // 유저 가입 일자

    @Builder
    public User(Integer id, String username, String email, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }
}



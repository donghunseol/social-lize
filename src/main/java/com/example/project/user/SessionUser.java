package com.example.project.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionUser {
    private Integer id; // 유저 번호
    private String username; // 로그인 아이디
    private String email; // 이메일
    private String password; // 비밀번호
    private LocalDateTime createdAt; // 유저 가입 일자

    @Builder
    public SessionUser(Integer id, String username, String email, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
    }
}



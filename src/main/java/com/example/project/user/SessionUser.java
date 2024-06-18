package com.example.project.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionUser {
    private Integer id; // 유저 번호
    private String nicName; // 로그인 아이디
    private String email; // 이메일
    private String password; // 비밀번호
    private LocalDateTime createdAt; // 유저 가입 일자

    @Builder
    public SessionUser(Integer id, String nicName, String email, String password, LocalDateTime createdAt) {
        this.id = id;
        this.nicName = nicName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nicName = user.getNickname();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
    }
}



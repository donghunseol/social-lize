package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project._core.enums.UserStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "user_tb")
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 유저 번호

    @Column(nullable = false, unique = true)
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}",
            message = "올바르지 않은 이메일 형식입니다.")
    private String email; // 이메일

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String nickname; // 유저 이름

    private String image; // 프로필 사진

    private String phone; // 전화 번호

    private LocalDate birth; // 생년 월일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserEnum role; // 권한

    @Enumerated(EnumType.STRING)
    private UserProviderEnum provider;

    private String providerId; //프로바이더 고유번호 ( 소셜로그인에서 제공하는 고유 식별번호, 카카오는 Long 타입 )

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @CreationTimestamp
    private LocalDateTime createdAt; // 유저 가입 일자

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatusEnum status;   // 유저 가입 상태 ( 정상:Normal, 차단:Banned )

    @Builder
    public User(Integer id, String email, String password, String nickname, String image, String phone, LocalDate birth, UserEnum role, UserProviderEnum provider, String providerId, LocalDateTime createdAt, UserStatusEnum status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
        this.phone = phone;
        this.birth = birth;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createdAt = createdAt;
        this.status = status;
    }
}



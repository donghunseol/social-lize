package com.example.project.social_member;

import com.example.project._core.enums.UserProviderEnum;
import lombok.Data;

import java.time.LocalDate;

public class SocialMemberResponse {

    // 소셜 멤버 리스트 DTO
    @Data
    public static class SocialMemberList {
        private Integer id;
        private String nickname;
        private String email;
        private LocalDate birth;
        private UserProviderEnum provider;

        public SocialMemberList(Integer id, String nickname, String email, LocalDate birth, UserProviderEnum provider) {
            this.id = id;
            this.nickname = nickname;
            this.email = email;
            this.birth = birth;
            this.provider = provider;
        }
    }
}

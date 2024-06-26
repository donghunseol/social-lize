package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

//요청 바디 데이터 관리
public class UserRequest {
    @Data
    public static class UpdateDTO{
        private String password;
        private String nickname;
        private String birth;
        private String phone;
        private MultipartFile image;
    }

    @Data
    public static class JoinDTO{
        private String email;
        private String password;
        private String name;
        private String year;
        private String month;
        private String day;
        private String birthdate;
        private UserEnum role;
        private UserProviderEnum provider;
        private String providerId;
    }
    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }

}

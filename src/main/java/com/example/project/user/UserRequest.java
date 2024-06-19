package com.example.project.user;

import lombok.Data;

//요청 바디 데이터 관리
public class UserRequest {
    @Data
    public static class JoinDTO{
        private String email;
        private String password;
        private String name;
        private String year;
        private String month;
        private String day;
    }
//    @Data
//    public static class LoginDTO{
//        private String username;
//        private String password;
//    }

}

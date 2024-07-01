package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project._core.enums.UserStatusEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

//요청 바디 데이터 관리
public class UserRequest {

//    @Data
//    public static class JoinDTO {
//        private String name;
//        private UserProviderEnum provider;
//        private String providerId;
//
//
//    }

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

        public JoinDTO(KakaoResponse.KakaoUserDTO kakaoUserDTO) {
            this.providerId = kakaoUserDTO.getId();
            this.name = kakaoUserDTO.getProperties().getNickname();
            this.provider = UserProviderEnum.KAKAO;
        }

        public JoinDTO(NaverResponse.NaverUserDTO naverUserDTO) {
            this.providerId = naverUserDTO.getResponse().getId();
            this.name = naverUserDTO.getResponse().getName();
            this.provider = UserProviderEnum.NAVER;
        }

        public JoinDTO(){
        }
    }
    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }

}

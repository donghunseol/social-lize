package com.example.project.user;

import com.example.project._core.enums.UserProviderEnum;
import lombok.Data;

@Data
public class JoinDTO {
    private String name;
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
}

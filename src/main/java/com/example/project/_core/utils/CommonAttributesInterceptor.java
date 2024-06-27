package com.example.project._core.utils;

import com.example.project.social.SocialService;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CommonAttributesInterceptor implements HandlerInterceptor {
    private final UserUtil userUtil;
    private final SocialService socialService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 공통 속성 설정
//        request.setAttribute("headerTitle", "Welcome to My Page");
//        request.setAttribute("footerMessage", "Footer content here");
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        if(sessionUser == null) {
            return true;
        }
        List<UserResponse.MainDTO.MySocialDTO> socialList = socialService.getMySocialList(sessionUser.getId());
        request.setAttribute("modelList", socialList);

        // true를 반환하여 요청을 계속 처리하도록 함
        return true;
    }
}
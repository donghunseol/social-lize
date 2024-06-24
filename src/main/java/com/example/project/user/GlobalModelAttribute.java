package com.example.project.user;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalModelAttribute {
    /*
        레디스에 저장된 값을 Mustache에서 바로 부르려면 불러지지 않는다.
        그래서 아래의 코드로 sessionUser값을 전역으로 설정해준다.
     */
    private final RedisTemplate<String, Object> rt;

    @ModelAttribute("sessionUser")
    public UserResponse.LoggedInUserDTO addSessionUserToModel(   ) {
        UserResponse.LoggedInUserDTO sessionUser =
                (UserResponse.LoggedInUserDTO) rt.opsForValue().get("sessionUser");
        log.info("전역 세선 유저 {} 👍👍👍👍👍👍👍",(UserResponse.LoggedInUserDTO) rt.opsForValue().get("sessionUser"));
        if (sessionUser != null) {
            return sessionUser;
        }
        return null;
    }
}
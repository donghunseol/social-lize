package com.example.project._core.utils;

import com.example.project.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserUtil {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String SESSIONUSER = "sessionUser";
    public void saveSessionUser(UserResponse.LoggedInUserDTO sessionUser) {
        log.info("sessionUser {} " , sessionUser);
        redisTemplate.opsForValue().set(SESSIONUSER, sessionUser);
    }

    public UserResponse.LoggedInUserDTO getSessionUser() {
        Object sessionUser = redisTemplate.opsForValue().get(SESSIONUSER);
        log.info("Session 👍👍👍👍👍👍{}", sessionUser);
        if (sessionUser != null) {
            log.info("Retrieved sessionUserJson: {}" , sessionUser );
            return (UserResponse.LoggedInUserDTO) sessionUser;
        } else{
            log.error("Session User is NULL");
            return null;
        }
    }
}
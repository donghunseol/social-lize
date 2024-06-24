package com.example.project._core.utils;

import com.example.project.user.SessionUser;
import com.example.project.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

//로그인된 사용자의 정보를 저장하기위해 사용하는 LoggedInUserDTO는 Jackson직렬화 유틸에서 지원하지않는 LocalDate를 사용하기때문에
//이를 해결하기위해 커스텀 직렬화, 역직렬화 함수를 만들어 사용한다.
//public class UserUtil {
//    public static UserResponse.LoggedInUserDTO getLoggedInUser(HttpSession session) {
//        String serializedUser = (String) session.getAttribute("sessionUser");
//        if (serializedUser == null) {
//            return null;
//        }
//        try {
//            return JsonRedisSerializer.deserialize(serializedUser, UserResponse.LoggedInUserDTO.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace(); // 에러 처리
//            return null;
//        }
//    }
//
//    public static void saveLoginUser(HttpSession session, UserResponse.LoggedInUserDTO user) {
//        try {
//            String serializedUser = JsonRedisSerializer.serialize(user);
//            session.setAttribute("sessionUser", serializedUser);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace(); // 에러 처리
//        }
//    }
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

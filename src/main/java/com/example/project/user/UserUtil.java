package com.example.project.user;

import com.example.project._core.utils.JsonRedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;

public class UserUtil {
    public static UserResponse.LoggedInUserDTO getLoggedInUser(HttpSession session) {
        String serializedUser = (String) session.getAttribute("sessionUser");
        if (serializedUser == null) {
            return null;
        }
        try {
            return JsonRedisSerializer.deserialize(serializedUser, UserResponse.LoggedInUserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // 에러 처리
            return null;
        }
    }

    public static void saveLoginUser(HttpSession session, UserResponse.LoggedInUserDTO user) {
        try {
            String serializedUser = JsonRedisSerializer.serialize(user);
            session.setAttribute("sessionUser", serializedUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // 에러 처리
        }
    }
}

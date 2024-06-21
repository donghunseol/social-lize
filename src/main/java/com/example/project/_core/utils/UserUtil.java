package com.example.project._core.utils;

import com.example.project.user.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;

//로그인된 사용자의 정보를 저장하기위해 사용하는 LoggedInUserDTO는 Jackson직렬화 유틸에서 지원하지않는 LocalDate를 사용하기때문에
//이를 해결하기위해 커스텀 직렬화, 역직렬화 함수를 만들어 사용한다.
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

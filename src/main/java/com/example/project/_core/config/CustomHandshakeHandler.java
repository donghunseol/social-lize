package com.example.project._core.config;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * WebSocket 핸드쉐이크 과정에서 사용자를 결정하는 커스텀 핸드쉐이크 핸들러입니다.
 * HTTP 세션에서 로그인된 사용자 정보를 가져와 Principal 객체로 변환하여 반환합니다.
 */
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    // HTTP 요청 및 WebSocket 핸들러에서 전달된 속성들을 기반으로 사용자를 결
    // 정합니다.
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler, Map<String, Object> attributes) {
        System.out.println("===== CustomHandshakeHandler");

        // HTTP 세션을 가져옵니다.
        HttpSession session = (HttpSession) attributes.get("HTTP_SESSION");
        if (session != null) {
            UserResponse.LoggedInUserDTO sessionUser = (UserResponse.LoggedInUserDTO) session.getAttribute("sessionUser");
            if (sessionUser != null) {
                // 사용자 ID를 문자열로 변환하여 Principal 객체를 생성하여 반환합니다.
                String userId = String.valueOf(sessionUser.getId());
                return new Principal() {
                    @Override
                    public String getName() {
                        return userId;
                    }
                };
            }
        }
        return null;
    }
}

package com.example.project._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration  // 이 클래스가 스프링 설정 클래스임을 나타냄
@EnableWebSocketMessageBroker  // WebSocket 메시지 브로커를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 알림 및 메시지 브로커 구성
        registry.enableSimpleBroker("/topic"); // "/topic"으로 시작하는 메시지는 브로커로 보내짐
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 메시지를 보낼 때 사용하는 애플리케이션 목적지 접두어 설정
        registry.setUserDestinationPrefix("/user");  // 일반적으로 자동 설정됨
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 채팅
        registry.addEndpoint("/chat-websocket").withSockJS(); // STOMP 엔드포인트를 등록하고 SockJS를 사용하도록 설정

        // 알림
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();
    }
}

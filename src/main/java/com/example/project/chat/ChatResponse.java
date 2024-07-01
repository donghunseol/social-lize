package com.example.project.chat;

import lombok.Data;

import java.time.LocalDateTime;

public class ChatResponse {

    // 실시간 채팅 응답 DTO
    @Data
    public static class LiveChatDTO {
        private Integer id;
        private Integer socialId;
        private Integer userId;
        private String comment;
        private LocalDateTime createdAt;

        // 모든 필드를 받는 생성자
        public LiveChatDTO(Integer id, Integer socialId, Integer userId, String comment, LocalDateTime createdAt) {
            this.id = id;
            this.socialId = socialId;
            this.userId = userId;
            this.comment = comment;
            this.createdAt = createdAt;
        }
    }
}

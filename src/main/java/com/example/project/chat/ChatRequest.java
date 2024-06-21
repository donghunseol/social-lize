package com.example.project.chat;

import lombok.Data;

public class ChatRequest {

    // 실시간 채팅 리퀘스트
    @Data
    public static class LiveChatDTO {
        private Integer socialId;
        private Integer userId;
        private String comment;


        // 모든 필드를 받는 생성자
        public LiveChatDTO(Integer socialId, Integer userId, String comment) {
            this.socialId = socialId;
            this.userId = userId;
            this.comment = comment;
        }
    }
}

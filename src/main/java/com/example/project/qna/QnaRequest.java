package com.example.project.qna;

import com.example.project._core.enums.QnaEnum;
import com.example.project.user.User;
import lombok.Data;

public class QnaRequest {
    @Data
    public static class SaveDTO {
        private String title;
        private String content;

        public Qna toEntity(User userId) {
            return Qna.builder()
                    .userId(userId)
                    .title(title)
                    .content(content)
                    .state(QnaEnum.WAITING)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }

    @Data
    public static class replyDTO {
        private String replyContent;
    }
}

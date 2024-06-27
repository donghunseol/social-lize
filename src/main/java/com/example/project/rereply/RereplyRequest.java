package com.example.project.rereply;

import com.example.project.reply.Reply;
import com.example.project.user.User;
import lombok.Data;

public class RereplyRequest {
    @Data
    public static class SaveDTO {
        private Integer replyId;
        private String comment;

        public Rereply toEntity(User sessionUser, Reply reply){
            return Rereply.builder()
                    .userId(sessionUser)
                    .replyId(reply)
                    .comment(comment)
                    .build();
        }
    }
}

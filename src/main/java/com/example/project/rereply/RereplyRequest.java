package com.example.project.rereply;

import com.example.project._core.enums.ReplyEnum;
import com.example.project.reply.Reply;
import com.example.project.user.User;
import lombok.Data;

public class RereplyRequest {
    @Data
    public static class SaveDTO {
        private String comment;

        public Rereply toEntity(User sessionUser, Reply parentReply) {
            return Rereply.builder()
                    .userId(sessionUser)
                    .replyId(parentReply)
                    .comment(comment)
                    .build();
        }
    }
}

package com.example.project.reply;

import com.example.project._core.enums.ReplyEnum;
import com.example.project.board.Board;
import com.example.project.user.User;
import lombok.Data;

import java.time.LocalDateTime;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private String comment;

        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .userId(sessionUser)
                    .boardId(board)
                    .comment(comment)
                    .state(ReplyEnum.ACTIVE)
                    .build();
        }
    }
}
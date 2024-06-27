package com.example.project.reply;

import com.example.project.board.Board;
import com.example.project.user.User;
import lombok.Data;

import java.time.LocalDateTime;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .userId(sessionUser)
                    .boardId(board)
                    .comment(comment)
                    .build();
        }
    }
}
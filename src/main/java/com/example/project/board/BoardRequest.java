package com.example.project.board;

import com.example.project._core.enums.BoardEnum;
import com.example.project.social.Social;
import com.example.project.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String content;

        public Board toEntity(Social socialId, User userId) {
            return Board.builder()
                    .userId(userId)
                    .socialId(socialId)
                    .content(content)
                    .role(BoardEnum.POST)
                    .build();
        }
    }
}

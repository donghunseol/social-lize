package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.enums.BoardEnum;
import com.example.project._core.enums.DeleteStateEnum;
import com.example.project.album.Album;
import com.example.project.hashtag.Hashtag;
import com.example.project.social.Social;
import com.example.project.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String content;
        private List<MultipartFile> imgFiles;
        private List<MultipartFile> videoFiles;
        private String[] hashtags;

        public SaveDTO(String[] hashtags) {
            this.hashtags = hashtags;
        }

        public Board boardToEntity(Social socialId, User userId) {
            return Board.builder()
                    .userId(userId)
                    .socialId(socialId)
                    .content(content)
                    .role(BoardEnum.POST)
                    .state(DeleteStateEnum.ACTIVE)
                    .build();
        }

        public Album albumToEntity(User userId, Board boardId, String path, AlbumEnum type) {
            return Album.builder()
                    .userId(userId)
                    .boardId(boardId)
                    .path(path)
                    .type(type)
                    .build();
        }

        public Album albumVideoToEntity(User userId, Board boardId, String hlsPath, String path, AlbumEnum type) {
            Album album = Album.builder()
                    .userId(userId)
                    .boardId(boardId)
                    .path(path)
                    .type(type)
                    .build();
            album.setHlsPath(hlsPath);
            return album;
        }
    }
}

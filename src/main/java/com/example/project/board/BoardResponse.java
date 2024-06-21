package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project.album.Album;
import com.example.project.like.Like;
import com.example.project.reply.Reply;
import com.example.project.social.SocialResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BoardResponse {

    @Data
    public static class BoardListDTO {
        private List<BoardDTO> boards;

        public BoardListDTO(List<BoardDTO> boards) {
            this.boards = boards;
        }

        @Data
        public static class BoardDTO {
            private Integer boardId;
            private String socialName;
            private String nickname;
            private String createdAt;
            private String content;
            private Integer likeCount;
            private Integer replyCount;
            private List<AlbumDTO> albums;
            private String userImage;
            private Boolean liked;

            public BoardDTO(Board board, Integer likeCount, Integer replyCount, List<AlbumDTO> albums, String userImage, Boolean liked) {
                this.boardId = board.getId();
                this.socialName = board.getSocialId().getName();
                this.nickname = board.getUserId().getNickname();
                LocalDateTime format = board.getCreatedAt();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm");
                this.createdAt = format.format(formatter);
                this.content = board.getContent();
                this.likeCount = likeCount;
                this.replyCount = replyCount;
                this.albums = albums;
                this.userImage = userImage;
                this.liked = liked;
            }
        }

        @Data
        public static class AlbumDTO {
            private String path;
            private AlbumEnum type;
            private boolean isVideo;
            private boolean isImage;

            public AlbumDTO(Album album) {
                this.path = album.getPath();
                this.type = album.getType();
                this.isVideo = (this.type == AlbumEnum.VIDEO);
                this.isImage = (this.type == AlbumEnum.IMAGE);
            }
        }
    }

}

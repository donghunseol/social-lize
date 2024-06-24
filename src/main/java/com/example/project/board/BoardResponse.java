package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project.album.Album;
import com.example.project.hashtag.Hashtag;
import com.example.project.like.Like;
import com.example.project.reply.Reply;
import com.example.project.social.SocialResponse;
import com.example.project.user.User;
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
            private Boolean book;
            private List<HashtagDTO> hashtagList;

            public BoardDTO(Board board, Integer likeCount, Integer replyCount, List<AlbumDTO> albums, String userImage, Boolean liked, Boolean book, List<Hashtag> hashtags) {
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
                this.book = book;
                this.hashtagList = hashtags.stream().map(HashtagDTO::new).toList();
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

        @Data
        public static class HashtagDTO {
            private Integer id;
            private String name;

            public HashtagDTO(Hashtag hashtag) {
                this.id = hashtag.getId();
                this.name = hashtag.getName();
            }
        }
    }

    @Data
    public static class BoardDetailDTO {
        private Integer boardId;
        private String nickname;
        private String createdAt;
        private String content;
        private Integer likeCount;
        private Integer replyCount;
        private List<HashtagDTO> hashtagList;
        private String userImage;

        public BoardDetailDTO(Board board, User user, Integer likeCount, Integer replyCount, List<Hashtag> hashtagList) {
            this.boardId = board.getId();
            this.nickname = user.getNickname();
            LocalDateTime format = board.getCreatedAt();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm");
            this.createdAt = format.format(formatter);
            this.content = board.getContent();
            this.likeCount = likeCount;
            this.replyCount = replyCount;
            this.hashtagList = hashtagList.stream().map(HashtagDTO::new).toList();
            this.userImage = user.getImage();
        }

        @Data
        public static class HashtagDTO {
            private String name;

            public HashtagDTO(Hashtag hashtag) {
                this.name = hashtag.getName();
            }
        }
    }
}

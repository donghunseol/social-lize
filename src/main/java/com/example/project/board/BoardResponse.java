package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.album.Album;
import com.example.project.hashtag.Hashtag;
import com.example.project.social.Social;
import com.example.project.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BoardResponse {

    @Data
    public static class SocialDetailDTO {
        private List<AllHasTagDTO> allHasTagList;
        private String title;
        private String name;
        private Integer memberCount;
        private String info;
        private String image;
        private String createdAt;
        private Integer boardCount;
        private String week;
        private List<String> weekList;
        private List<Integer> weekCount;


        @Data
        public static class AllHasTagDTO {
            private Integer hasTagId;
            private String hasTagName;

            public AllHasTagDTO(Hashtag hashtag) {
                this.hasTagId = hashtag.getId();
                this.hasTagName = hashtag.getName();
            }
        }

        private List<BoardDTO> boards;

        public SocialDetailDTO(List<Hashtag> allHasTagList, Social social, String name, Integer memberCount, List<BoardDTO> boards, Integer boardCount, String week, List<String> weekList, List<Integer> weekCount) {
            this.allHasTagList = allHasTagList.stream().map(AllHasTagDTO::new).toList();
            this.title = social.getName();
            this.name = name;
            this.memberCount = memberCount;
            this.info = social.getInfo();
            this.boards = boards;
            this.image = social.getImage();
            LocalDateTime format = social.getCreatedAt();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월");
            this.createdAt = format.format(formatter);
            this.boardCount = boardCount;
            this.week = week;
            this.weekList = weekList;
            this.weekCount = weekCount;
        }

        public SocialDetailDTO(List<BoardDTO> boards) {
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
            private String myProfileImage;
            private Boolean hashEmpty;

            public BoardDTO(Board board, Integer likeCount, Integer replyCount, List<AlbumDTO> albums, String userImage, Boolean liked, Boolean book, List<Hashtag> hashtags, String myProfileImage, Boolean hashEmpty) {
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
                this.myProfileImage = myProfileImage;
                this.hashEmpty = hashEmpty;
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

    // 게시글 리스트 (관리자)
    @AllArgsConstructor
    @Data
    public static class BoardList {
        private Integer boardId;
        private String socialName;
        private String nickname;
        private String content;
        private LocalDateTime createdAt;
        private String formattedCreatedAt;

        public BoardList(Integer boardId, String socialName, String nickname, String content, LocalDateTime createdAt) {
            this.boardId = boardId;
            this.socialName = socialName;
            this.nickname = nickname;
            this.content = content;
            this.formattedCreatedAt = LocalDateTimeFormatter.convert(createdAt);
        }
    }

    // 게시글 상세 DTO (관리자)
    @Data
    public static class Detail {
        private Integer boardId;
        private String socialName;
        private String nickname;
        private String content;
        private String createdAt;

        public Detail(Board board) {
            this.boardId = board.getId();
            this.socialName = board.getSocialId().getName();
            this.nickname = board.getUserId().getNickname();
            this.content = board.getContent();
            this.createdAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
        }
    }
}

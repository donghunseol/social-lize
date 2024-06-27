package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.album.Album;
import com.example.project.hashtag.Hashtag;
import com.example.project.reply.Reply;
import com.example.project.rereply.Rereply;
import com.example.project.social.Social;
import com.example.project.user.User;
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

        public SocialDetailDTO(List<Hashtag> allHasTagList, Social social, String name, Integer memberCount, List<BoardDTO> boards, Integer boardCount, String week, List<Integer> weekCount) {
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
        private List<ReplyDTO> replyList;

        public BoardDetailDTO(Board board, User user, Integer likeCount, Integer replyCount, List<Hashtag> hashtagList, List<ReplyDTO> replyList) {
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
            this.replyList = replyList;
        }

        @Data
        public static class HashtagDTO {
            private String name;

            public HashtagDTO(Hashtag hashtag) {
                this.name = hashtag.getName();
            }
        }

        @Data
        public static class ReplyDTO {
            private Integer id;
            private String userImage;
            private String nickname;
            private String content;
            private String createdAt;
            private List<RereplyDTO> rereplyList;

            public ReplyDTO(Reply reply, List<RereplyDTO> rereplyList) {
                this.id = reply.getId();
                this.userImage = reply.getUserId().getImage();
                this.nickname = reply.getUserId().getNickname();
                this.content = reply.getComment();
                LocalDateTime format = reply.getCreatedAt();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm");
                this.createdAt = format.format(formatter);
                this.rereplyList = rereplyList;
            }
        }

        @Data
        public static class RereplyDTO {
            private Integer id;
            private String userImage;
            private String nickname;
            private String content;
            private String createdAt;

            public RereplyDTO(Rereply rereply) {
                this.id = rereply.getId();
                this.userImage = rereply.getUserId().getImage();
                this.nickname = rereply.getUserId().getNickname();
                this.content = rereply.getComment();
                LocalDateTime format = rereply.getCreatedAt();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm");
                this.createdAt = format.format(formatter);
            }
        }
    }


    // 게시글 리스트 (관리자)
    @Data
    public static class BoardListDTO {
        private Integer count;
        private List<BoardList> boardList;

        public BoardListDTO(Integer count, List<BoardList> boardList) {
            this.count = count;
            this.boardList = boardList;
        }

        @Data
        public static class BoardList {
            private Integer boardId;
            private String socialName;
            private String nickname;
            private String content;
            private String createdAt;

            public BoardList(Board board) {
                this.boardId = board.getId();
                this.socialName = board.getSocialId().getName();
                this.nickname = board.getUserId().getNickname();
                this.content = board.getContent();
                this.createdAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
            }
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

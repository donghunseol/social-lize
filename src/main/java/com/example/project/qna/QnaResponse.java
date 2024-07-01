package com.example.project.qna;

import com.example.project._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.util.List;

public class QnaResponse {

    @Data
    public static class QnaAnswerListDTO {
        private Integer count;
        private List<ListDTO> list;

        public QnaAnswerListDTO(List<Qna> list, Integer count) {
            this.count = count;
            this.list = list.stream().map(ListDTO::new).toList();

        }

        @Data
        public static class ListDTO {
            private Integer id;
            private String title;
            private String content;

            public ListDTO(Qna qna) {
                this.id = qna.getId();
                this.title = qna.getTitle();
                this.content = qna.getContent();
            }
        }
    }

    @Data
    public static class QnaWaitingListDTO {
        private Integer count;
        private List<ListDTO> list;

        public QnaWaitingListDTO(List<Qna> list, Integer count) {
            this.count = count;
            this.list = list.stream().map(ListDTO::new).toList();

        }

        @Data
        public static class ListDTO {
            private Integer id;
            private String title;
            private String content;

            public ListDTO(Qna qna) {
                this.id = qna.getId();
                this.title = qna.getTitle();
                this.content = qna.getContent();
            }
        }
    }

    @Data
    public static class QnaDetailDTO {
        private Integer id;
        private String title;
        private String content;

        public QnaDetailDTO(Qna qna) {
            this.id = qna.getId();
            this.title = qna.getTitle();
            this.content = qna.getContent();
        }
    }

    // 문의 리스트 조회 (관리자)
    @Data
    public static class QnaListAndCount {
        private List<QnaList> qnaList;
        private Integer count;

        public QnaListAndCount(List<QnaList> qnaList, Integer count) {
            this.qnaList = qnaList;
            this.count = count;
        }

        @Data
        public static class QnaList {
            private Integer id;
            private String title;
            private String nickname;
            private String createdAt;

            public QnaList(Qna qna) {
                this.id = qna.getId();
                this.title = qna.getTitle();
                this.nickname = qna.getUserId().getNickname();
                this.createdAt = LocalDateTimeFormatter.convert(qna.getCreatedAt());
            }
        }
    }

    // 문의 상세 조회 (관리자)
    @Data
    public static class QnaDetail {
        private Integer id;
        private String nickname;
        private String title;
        private String content;
        private String replyContent;

        public QnaDetail(Qna qna) {
            this.id = qna.getId();
            this.nickname = qna.getUserId().getNickname();
            this.title = qna.getTitle();
            this.content = qna.getContent();
            this.replyContent = qna.getReplyContent();
            if (replyContent == null) {
                this.replyContent = "";
            }
        }
    }
}

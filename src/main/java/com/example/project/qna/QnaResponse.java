package com.example.project.qna;

import lombok.Data;

import java.util.List;

public class QnaResponse {

    @Data
    public static class QnaListDTO {
        private Integer count;
        private List<ListDTO> list;

        public QnaListDTO(List<Qna> list, Integer count) {
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
}

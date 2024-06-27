package com.example.project.notice;

import com.example.project._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.util.List;

public class NoticeResponse {

    // 공지사항 DTO
    @Data
    public static class NoticeDTO {
        private Integer count;
        private List<ListDTO> noticeList;

        public NoticeDTO(Integer count, List<ListDTO> noticeList) {
            this.count = count;
            this.noticeList = noticeList;
        }

        // 공지 리스트 DTO
        @Data
        public static class ListDTO {
            private Integer id;
            private String content;
            private String createdAt;

            public ListDTO(Notice notice) {
                this.id = notice.getId();
                this.content = notice.getBoard().getContent();
                this.createdAt = LocalDateTimeFormatter.convert(notice.getBoard().getCreatedAt());
            }
        }
    }

    // 공지 상세 DTO
    @Data
    public static class DetailDTO {
        private Integer id;
        private String content;

        public DetailDTO(Notice notice) {
            this.id = notice.getId();
            this.content = notice.getBoard().getContent();
        }
    }
}

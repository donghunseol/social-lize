package com.example.project.file;

import lombok.Data;

public class FileRequest {

    @Data
    public static class FileUploadRequest {
        private Integer socialId; // 소셜 번호
        private Integer userId; // 유저 번호
        private Integer boardId; // 게시글 번호

        private String name; // 파일 이름
        private String path; // 파일 경로
    }
}

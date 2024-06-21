package com.example.project.social;

import lombok.Data;

import java.util.List;

public class SocialRequest {

    // 소셜 생성 DTO
    @Data
    public static class Create {
        private Integer userId;
        private String name;
        private String image;
        private String info;
        private List<Category> categories;
    }

    // 소셜 수정 DTO
    @Data
    public static class Update {
        private String name;
        private String image;
        private String info;
        private List<Category> categories;
    }

    // 카테고리 DTO
    @Data
    public static class Category {
        private Integer categoryNameId;
    }
}

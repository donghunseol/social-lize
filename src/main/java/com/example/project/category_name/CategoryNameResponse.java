package com.example.project.category_name;

import lombok.Data;

public class CategoryNameResponse {

    // 카테고리 리스트 DTO
    @Data
    public static class CategoryDTO {
        private Integer id;
        private String name;
        private String imagePath;

        public CategoryDTO(CategoryName categoryName) {
            this.id = categoryName.getId();
            this.name = categoryName.getName();
            this.imagePath = categoryName.getImagePath();
        }
    }

    // 카테고리 상세 DTO
    @Data
    public static class Detail {
        private Integer id;
        private String name;
        private String imagePath;

        public Detail(CategoryName categoryName) {
            this.id = categoryName.getId();
            this.name = categoryName.getName();
            this.imagePath = categoryName.getImagePath();
        }
    }
}

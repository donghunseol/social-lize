package com.example.project.category_name;

import lombok.Data;

import java.util.List;

public class CategoryNameResponse {

    // 카테고리 리스트 DTO
    @Data
    public static class CategoryListDTO {
        private Integer count;
        private List<CategoryList> categoryList;

        public CategoryListDTO(Integer count, List<CategoryList> categoryList) {
            this.count = count;
            this.categoryList = categoryList;
        }

        @Data
        public static class CategoryList {
            private Integer id;
            private String name;
            private String imagePath;

            public CategoryList(CategoryName categoryName) {
                this.id = categoryName.getId();
                this.name = categoryName.getName();
                this.imagePath = categoryName.getImagePath();
            }
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

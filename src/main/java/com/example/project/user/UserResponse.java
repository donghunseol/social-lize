package com.example.project.user;

import com.example.project.category_name.CategoryName;
import lombok.Data;

import java.util.List;

public class UserResponse {

    @Data
    public static class MainDTO {
        private List<MySocialDTO> mySocialList;
        private List<MySocialPopularDTO> mySocialPopularList;
        private List<PopularPostDTO> popularPostList;
        private List<CategoryDTO> categoryList;
        private List<CategorySocialDTO> categorySocialList;

        public MainDTO(List<Object[]> mySocialList,
                       List<Object[]> mySocialPopularList,
                       List<Object[]> popularPostList,
                       List<CategoryName> categoryList,
                       List<Object[]> categorySocialList) {
            this.mySocialList = mySocialList.stream().map(MySocialDTO::new).toList();
            this.mySocialPopularList = mySocialPopularList.stream().map(MySocialPopularDTO::new).toList();
            this.popularPostList = popularPostList.stream().map(PopularPostDTO::new).toList();
            this.categoryList = categoryList.stream().map(CategoryDTO::new).toList();
            this.categorySocialList = categorySocialList.stream().map(CategorySocialDTO::new).toList();
        }

        @Data
        private static class MySocialDTO {
            private Integer id;
            private String title;
            private String imagePath;
            private Long memberCount;

            public MySocialDTO(Object[] mySocialList) {
                this.id = (Integer) mySocialList[0];
                this.title = (String) mySocialList[1];
                this.imagePath = (String) mySocialList[2];
                this.memberCount = (Long) mySocialList[3];
            }
        }

        @Data
        private static class MySocialPopularDTO {
            private Integer id;
            private String title;
            private String content;
            private String nickname;
            private Long likeCount;
            private Long replyCount;

            public MySocialPopularDTO(Object[] mySocialPopularList) {
                this.id = (Integer) mySocialPopularList[0];
                this.title = (String) mySocialPopularList[1];
                this.content = (String) mySocialPopularList[2];
                this.nickname = (String) mySocialPopularList[3];
                this.likeCount = (Long) mySocialPopularList[4];
                this.replyCount = (Long) mySocialPopularList[5];
            }
        }

        @Data
        private static class PopularPostDTO {
            private Integer id;
            private String title;
            private String content;
            private String nickname;
            private Long likeCount;
            private Long replyCount;

            public PopularPostDTO(Object[] popularPostList) {
                this.id = (Integer) popularPostList[0];
                this.title = (String) popularPostList[1];
                this.content = (String) popularPostList[2];
                this.nickname = (String) popularPostList[3];
                this.likeCount = (Long) popularPostList[4];
                this.replyCount = (Long) popularPostList[5];
            }
        }

        @Data
        private static class CategoryDTO {
            private Integer id;
            private String imagePath;
            private String name;

            public CategoryDTO(CategoryName categoryName) {
                this.id = categoryName.getId();
                this.imagePath = categoryName.getImagePath();
                this.name = categoryName.getName();
            }
        }

        @Data
        private static class CategorySocialDTO {
            private Integer id;
            private String name;
            private String image;
            private String info;
            private Long memberCount;

            public CategorySocialDTO(Object[] categorySocialList) {
                this.id = (Integer) categorySocialList[0];
                this.name = (String) categorySocialList[1];
                this.image = (String) categorySocialList[2];
                this.info = (String) categorySocialList[3];
                this.memberCount = (Long) categorySocialList[4];
            }
        }
    }
}

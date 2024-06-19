package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project.category_name.CategoryName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {
    //로그인된 사용자의 DTO
    @Data
    public static class LoggedInUserDTO {
        private Integer id; // 유저 번호
        private String email; // 이메일
        private String nickname; // 유저 이름
        private String image; // 프로필 사진
        private String phone; // 전화 번호
        private LocalDate birth; // 생년 월일
        private UserEnum role; // 권한
        private UserProviderEnum provider;
        private String provide_id; //프로바이더 고유번호 ( 소셜로그인에서 제공하는 고유 식별번호, 카카오는 Long 타입 )
        private LocalDateTime createdAt; // 유저 가입 일자

        public LoggedInUserDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.image = user.getImage();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.role = user.getRole();
            this.provider = user.getProvider();
            this.provide_id = user.getProvide_id();
            this.createdAt = user.getCreatedAt();
        }
    }


    @Data
    public static class MainDTO {
        private List<MySocialDTO> mySocialList;
        private List<MySocialPopularDTO> mySocialPopularList;
        private List<PopularPostDTO> popularPostList;
        private List<CategoryDTO> categoryList;

        public MainDTO(List<Object[]> mySocialList,
                       List<Object[]> mySocialPopularList,
                       List<Object[]> popularPostList,
                       List<CategoryName> categoryList) {
            this.mySocialList = mySocialList.stream().map(MySocialDTO::new).toList();
            this.mySocialPopularList = mySocialPopularList.stream().map(MySocialPopularDTO::new).toList();
            this.popularPostList = popularPostList.stream().map(PopularPostDTO::new).toList();
            this.categoryList = categoryList.stream().map(CategoryDTO::new).toList();
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
            private String imagePath;
            private String name;

            public CategoryDTO(CategoryName categoryName) {
                this.imagePath = categoryName.getImagePath();
                this.name = categoryName.getName();
            }
        }
    }
}

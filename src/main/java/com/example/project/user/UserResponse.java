package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project._core.enums.UserStatusEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.category_name.CategoryName;
import lombok.Data;

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
        private String providerId; //프로바이더 고유번호 ( 소셜로그인에서 제공하는 고유 식별번호, 카카오는 Long 타입 )
        private LocalDateTime createdAt; // 유저 가입 일자
        private UserStatusEnum status;// 가입상태(정상, 차단)

        public LoggedInUserDTO() {
        }

        public LoggedInUserDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.image = user.getImage();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.role = user.getRole();
            this.provider = user.getProvider();
            this.providerId = user.getProviderId();
            this.createdAt = user.getCreatedAt();
            this.status = user.getStatus();
        }
    }


    @Data
    public static class MainDTO {
        private List<MySocialDTO> mySocialList;
        private List<MySocialPopularDTO> mySocialPopularList;
        private List<PopularPostDTO> popularPostList;
        private List<CategoryDTO> categoryList;
        private List<CategoryNameDTO> categoryNameList;

        public MainDTO(List<Object[]> mySocialList,
                       List<Object[]> mySocialPopularList,
                       List<Object[]> popularPostList,
                       List<Object[]> categoryList,
                       List<CategoryName> categoryNameList) {

            this.mySocialList = mySocialList.stream().map(MySocialDTO::new).toList();
            this.mySocialPopularList = mySocialPopularList.stream().map(MySocialPopularDTO::new).toList();
            this.popularPostList = popularPostList.stream().map(PopularPostDTO::new).toList();
            this.categoryList = categoryList.stream().map(CategoryDTO::new).toList();
            this.categoryNameList = categoryNameList.stream().map(CategoryNameDTO::new).toList();
        }

        @Data
        public static class MySocialDTO {
            private Integer id;
            private String name;
            private String imagePath;
            private Long memberCount;
            private String info;

            public MySocialDTO(Object[] mySocialList) {
                this.id = (Integer) mySocialList[0];
                this.name = (String) mySocialList[1];
                this.imagePath = (String) mySocialList[2];
                this.memberCount = (Long) mySocialList[3];
                this.info = (String) mySocialList[4];
            }
        }

        @Data
        private static class MySocialPopularDTO {
            private Integer id;
            private String content;
            private String nickname;
            private Long likeCount;
            private Long replyCount;
            private String socialName;
            private String socialImage;

            public MySocialPopularDTO(Object[] mySocialPopularList) {
                this.id = (Integer) mySocialPopularList[0];
                this.content = (String) mySocialPopularList[1];
                this.nickname = (String) mySocialPopularList[2];
                this.likeCount = (Long) mySocialPopularList[3];
                this.replyCount = (Long) mySocialPopularList[4];
                this.socialName = (String) mySocialPopularList[5];
                this.socialImage = (String) mySocialPopularList[6];
            }
        }

        @Data
        private static class PopularPostDTO {
            private Integer id;
            private String name;
            private String content;
            private Long likeCount;
            private Long replyCount;
            private String image;

            public PopularPostDTO(Object[] popularPostList) {
                this.id = (Integer) popularPostList[0];
                this.name = (String) popularPostList[1];
                this.content = (String) popularPostList[2];
                this.likeCount = (Long) popularPostList[3];
                this.replyCount = (Long) popularPostList[4];
                this.image = (String) popularPostList[5];
            }
        }

        @Data
        private static class CategoryDTO {
            private Integer id;
            private String socialName;
            private String image;
            private String info;
            private String categoryName;

            public CategoryDTO(Object[] categoryName) {
                this.id = (Integer) categoryName[0];
                this.socialName = (String) categoryName[1];
                this.image = (String) categoryName[2];
                this.info = (String) categoryName[3];
                this.categoryName = (String) categoryName[4];
            }
        }

        @Data
        private static class CategoryNameDTO {
            private Integer id;
            private String name;

            public CategoryNameDTO(CategoryName categoryName) {
                this.id = categoryName.getId();
                this.name = categoryName.getName();
            }
        }
    }

    @Data
    public static class MainAjaxDTO {
        private List<CategorySocialDTO> categorySocialList;

        public MainAjaxDTO(List<Object[]> categorySocialList) {
            this.categorySocialList = categorySocialList.stream().map(CategorySocialDTO::new).toList();
        }

        @Data
        private static class CategorySocialDTO {
            private Integer id;
            private String name;
            private String image;
            private String info;
            private Long memberCount;
            private String nickName;

            public CategorySocialDTO(Object[] categorySocialList) {
                this.id = (Integer) categorySocialList[0];
                this.name = (String) categorySocialList[1];
                this.image = (String) categorySocialList[2];
                this.info = (String) categorySocialList[3];
                this.memberCount = (Long) categorySocialList[4];
                this.nickName = (String) categorySocialList[5];
            }
        }
    }


    // 회원 리스트 조회 (관리자)
    @Data
    public static class UserListDTO {
        private Integer count;
        private List<UserList> userList;

        public UserListDTO(Integer count, List<UserList> userList) {
            this.count = count;
            this.userList = userList;
        }

        @Data
        public static class UserList {
            private Integer userId;
            private String email;
            private String nickname;
            private LocalDate birth;
            private UserProviderEnum provider;
            private String createdAt;

            public UserList(User user) {
                this.userId = user.getId();
                this.email = user.getEmail();
                this.nickname = user.getNickname();
                this.birth = user.getBirth();
                this.provider = user.getProvider();
                this.createdAt = LocalDateTimeFormatter.convert(user.getCreatedAt());

                if (user.getProvider() == null) {
                    setProvider(UserProviderEnum.Basic);
                }
            }
        }
    }

    // 회원 상세 조회 (관리자)
    @Data
    public static class UserDetail {
        private Integer userId;
        private String email;
        private String nickname;
        private String image;
        private String phone;
        private LocalDate birth;
        private UserProviderEnum provider;
        private String createdAt;

        public UserDetail(User user) {
            this.userId = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.image = user.getImage();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.provider = user.getProvider();
            this.createdAt = LocalDateTimeFormatter.convert(user.getCreatedAt());

            if (user.getProvider() == null) {
                setProvider(UserProviderEnum.Basic);
            }
        }
    }
}

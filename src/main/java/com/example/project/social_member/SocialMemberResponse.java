package com.example.project.social_member;

import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.social.Social;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SocialMemberResponse {

    @Data
    public static class ArticleCount{
        private Integer articleCount;
        private Integer replyCount;

        public ArticleCount(Integer articleCount, Integer replyCount) {
            this.articleCount = articleCount;
            this.replyCount = replyCount;
        }
    }

    @Data
    public static class MemberDTO {
        private String title;
        private Integer socialId;
        private String name;
        private Integer memberCount;
        private String info;
        private String image;
        private Boolean isManager;
        private List<WaitingDTO> waitingList;
        private Boolean isWaiting;

        public MemberDTO(Social social, String name, Integer memberCount, Boolean isManager, List<SocialMember> waitingList) {
            this.title = social.getName();
            this.socialId = social.getId();
            this.name = name;
            this.memberCount = memberCount;
            this.info = social.getInfo();
            this.image = social.getImage();
            this.isManager = isManager;

            if (!waitingList.isEmpty()) {
                this.isWaiting = true;
            } else {
                this.isWaiting = false;
            }

            this.waitingList = waitingList.stream().map(WaitingDTO::new).toList();
        }

        @Data
        public static class WaitingDTO {
            private Integer userId;
            private String userName;
            private String userImage;

            public WaitingDTO(SocialMember socialMember) {
                this.userId = socialMember.getUserId().getId();
                this.userName = socialMember.getUserId().getNickname();
                this.userImage = socialMember.getUserId().getImage();
            }
        }
    }

    @Data
    public static class SocialMemberDTO {
        private Integer id;
        private String nickname;
        private String email;
        private LocalDate birth;
        private UserProviderEnum provider;
        private String imagePath;
        private LocalDateTime createdAt;
        private String createdAtString;
        private SocialMemberRoleEnum role;
        private Boolean isManager;

        public SocialMemberDTO(SocialMember socialMember) {
            this.id = socialMember.getId();
            this.nickname = socialMember.getUserId().getNickname();
            this.email = socialMember.getUserId().getEmail();
            this.birth = socialMember.getUserId().getBirth();
            this.role = socialMember.getRole();
            this.imagePath = socialMember.getUserId().getImage();
            this.createdAt = socialMember.getUserId().getCreatedAt();
            this.createdAtString = LocalDateTimeFormatter.convert(socialMember.getUserId().getCreatedAt(), "yyyy년 MM월 dd일");
            this.isManager = socialMember.getRole() == SocialMemberRoleEnum.MANAGER;
        }
    }
}

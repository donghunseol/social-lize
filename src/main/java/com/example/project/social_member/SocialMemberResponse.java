package com.example.project.social_member;

import com.example.project._core.enums.UserProviderEnum;
import com.example.project.social.Social;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SocialMemberResponse {

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
}

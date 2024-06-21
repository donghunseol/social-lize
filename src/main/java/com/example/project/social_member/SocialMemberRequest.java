package com.example.project.social_member;

import com.example.project._core.enums.SocialMemberStateEnum;
import lombok.Data;

public class SocialMemberRequest {

    // 멤버 상태 업데이트 DTO
    @Data
    public static class UpdateStateDTO {
        private SocialMemberStateEnum newState;
    }

}

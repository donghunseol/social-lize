package com.example.project.file;

import com.example.project.social.Social;
import com.example.project.social_member.SocialMember;
import com.example.project.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class FileRequest {

    // 파일 입력 시 가져오는 데이터
    @Data
    public static class FileUploadDTO {
        private Integer socialId; // 소셜 번호
        private Integer userId; // 유저 번호

        private MultipartFile file; // 파일
    }
}

package com.example.project.social;

import com.example.project.category.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SocialRequest {

    // 소셜 생성 DTO
    @Data
    public static class Create {
        private String name;
        private MultipartFile image;
        private String info;
        private List<String> categories;
        private List<Integer> categoriesN;
    }

    // 소셜 수정 DTO
    @Data
    public static class Update {
        private String name;
        private MultipartFile image;
        private String info;
        private List<String> categories;
        private List<Integer> categoriesN;
    }
}

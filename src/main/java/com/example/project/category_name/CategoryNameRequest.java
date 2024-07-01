package com.example.project.category_name;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class CategoryNameRequest {

    @Data
    public static class Create {
        private String name;
        private MultipartFile imagePath;
    }

    @Data
    public static class Update {
        private String name;
        private MultipartFile imagePath;
    }
}

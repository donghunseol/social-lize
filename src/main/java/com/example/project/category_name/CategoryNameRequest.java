package com.example.project.category_name;

import lombok.Data;

public class CategoryNameRequest {

    @Data
    public static class Create {
        private String name;
        private String imagePath;
    }

    @Data
    public static class Update {
        private String name;
        private String imagePath;
    }
}

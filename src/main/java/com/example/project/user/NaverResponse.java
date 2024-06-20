package com.example.project.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class NaverResponse {
    @Data
    public static class TokenDTO {
        @JsonProperty("access_token") // 이걸 걸면 JSON 데이터가 위치에 들어간다.
        private String accessToken;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("expires_in")
        private String expiresIn;
    }

    @Data
    public static class NaverUserDTO {
        @JsonProperty("resultcode")
        private String resultcode;
        @JsonProperty("message")
        private String message;
        @JsonProperty("response")
        private Response response;

        @Data
        static class Response {
            @JsonProperty("id")
            private String id;
            @JsonProperty("email")
            private String email;
            @JsonProperty("name")
            private String name;
        }
    }
}

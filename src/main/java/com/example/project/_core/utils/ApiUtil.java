package com.example.project._core.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
public class ApiUtil<T> {

    public static <T> ApiResult<T> success(T response) {
        // 성공 시, 응답 데이터와 함께 ApiResult 객체를 생성합니다. 상태 코드는 200으로 설정합니다.
        return new ApiResult<>(true, response, 200, null);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        // 에러 발생 시, 에러 메시지와 HTTP 상태 코드를 포함한 ApiResult 객체를 생성합니다.
        return new ApiResult<>(false, null, status.value(), message);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiResult<T> {
        private boolean success; // API 요청 성공 여부
        private T response; // API 응답 데이터
        private int status; // HTTP 상태 코드
        private String errorMessage; // 에러 메시지
    }

    private Integer status;
    private String msg;
    private T body;

    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body = null;
    }
}
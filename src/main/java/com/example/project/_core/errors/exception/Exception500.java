package com.example.project._core.errors.exception;

import com.example.project._core.utils.ApiUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 서버 에러
@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ApiUtil.ApiResult<?> body() {
        return ApiUtil.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus status() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
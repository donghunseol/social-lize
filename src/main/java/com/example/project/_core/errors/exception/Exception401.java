package com.example.project._core.errors.exception;

import com.example.project._core.utils.ApiUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 인증 안됨
@Getter
public class Exception401 extends RuntimeException {
    public Exception401(String message) {
        super(message);
    }

    public ApiUtil.ApiResult<?> body() {
        return ApiUtil.error(getMessage(), HttpStatus.UNAUTHORIZED);
    }

    public HttpStatus status() {
        return HttpStatus.UNAUTHORIZED;
    }
}
package com.example.project._core.errors.exception;

import com.example.project._core.utils.ApiUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 유효성 검사 실패, 잘못된 파라메터 요청
@Getter
public class Exception400 extends RuntimeException {
    public Exception400(String message) {
        super(message);
    }

    public ApiUtil.ApiResult<?> body() {
        return ApiUtil.error(getMessage(), HttpStatus.BAD_REQUEST);
    }

    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
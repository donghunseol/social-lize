package com.example.project._core.errors.exception;

import lombok.Getter;

// 서버 에러
@Getter
public class Exception600 extends RuntimeException {
    public Exception600(String message) {
        super(message);
    }

}
package com.example.project._core.errors;

import com.example.project._core.errors.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
// RuntimeException이 터지면 해당 파일로 오류가 모인다
@ControllerAdvice // 데이터 응답
@Controller
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    @GetMapping("/400")
    public String ex400(Exception400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("400 : " + e.getMessage());
        return "/err/400";
    }

    @ExceptionHandler(Exception401.class)
    @GetMapping("/401")
    public String ex401(Exception401 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("401 : " + e.getMessage()); // ex) 로그인 실패 다이렉트 메세지 [위험도는 낮지만 주의해야 하는 점이 있다 : 강제로 접속하는 인원이 발생]
        log.warn("IP : " + request.getRemoteAddr()); // 누군지 IP 확인
        log.warn("Agent : " + request.getHeader("User-Agent")); // 장비 확인
        return "/err/401";
    }

    @ExceptionHandler(Exception403.class)
    @GetMapping("/403")
    public String ex403(Exception403 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("403 : " + e.getMessage());
        return "/err/403";
    }

    @ExceptionHandler(Exception404.class)
    @GetMapping("/404")
    public String ex404(Exception404 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.info("404 : " + e.getMessage());
        return "/err/404";
    }

    @ExceptionHandler(Exception500.class)
    @GetMapping("/500")
    public String ex500(Exception500 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        return "/err/500";
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/unknown")
    public String unknownServerError(Exception500 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        return "/err/500";
    }
}
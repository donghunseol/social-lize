package com.example.project._core.errors;


import com.example.project._core.errors.exception.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Aspect // AOP 등록
@Component // IoC 등록
public class MyValidationHandler {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void effectiveness(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 파라미터(매개변수)
        System.out.println("args/length : " + args.length);

        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if (errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        System.out.println(error.getField());
                        System.out.println(error.getDefaultMessage());

                        throw new Exception400(error.getDefaultMessage() + " : " + error.getField());
                    }
                }
            }
        }
    }
}
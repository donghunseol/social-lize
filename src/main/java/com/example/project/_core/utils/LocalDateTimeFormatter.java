package com.example.project._core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//자바 LocalDateTime 타입을 문자열로 바꿔주는 클래스 : yyyy-MM-dd HH:mm:ss로 변환한다.
public class LocalDateTimeFormatter {
    public static String convert(LocalDateTime localDateTime) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(customFormatter);
    }
}

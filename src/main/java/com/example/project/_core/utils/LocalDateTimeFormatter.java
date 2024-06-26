package com.example.project._core.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//자바 LocalDateTime 타입을 문자열로 바꿔주는 클래스 : yyyy-MM-dd HH:mm:ss로 변환한다.
public class LocalDateTimeFormatter {
    public static String convert(LocalDateTime localDateTime) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(customFormatter);
    }

    // 인수로 주어진 시간이 얼마전인지 표시해주는 매소드 ( 예: 1일전, 20분전, 3시간전 )
    public static String getDuration(LocalDateTime pastTime) {
//        LocalDateTime pastTime = LocalDateTime.of(2024, 6, 25, 13, 00);
        // 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        // 두 시간 사이의 차이 계산
        Duration duration = Duration.between(pastTime, now);
        // 차이를 분으로 변환
        long diffMinutes = duration.toMinutes();
        // 차이를 시간으로 변환
        long diffHours = duration.toHours();
        long diffDays = duration.toDays();
        long diffSeconds = duration.getSeconds();

        // 결과 출력
        if( diffDays >0 ) {
            return diffDays + "일 전";
        }else if( diffHours >0 ){
            return diffHours + "시간 전";
        }else if( diffMinutes > 0) {
            return diffMinutes + "분 전";
        } else if (diffSeconds > 0) {
            return diffSeconds + "초 전";
        }else return null;
    }
}

package com.example.project.notification;

import com.example.project.user.User;
import com.example.project.user.UserQueryRepository;
import com.example.project.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Import(UserQueryRepository.class)
@DataJpaTest
public class NotificationTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void updateAllChecked(){
        Integer count = notificationRepository.getUnCheckedCountByUserId(1);
        System.out.println(count);

        notificationRepository.updateAllCheckedById(1);

        count = notificationRepository.getUnCheckedCountByUserId(1);
        System.out.println(count);
    }

    @Test
    public void test() {
//        User user = new User().builder().id(1).build();
//        User user = new User();
//        user.setId(1);
        Integer count = notificationRepository.getUnCheckedCountByUserId(1);
        System.out.println(count);

        List<Notification> notificationList = notificationRepository.getAllByUserId(1);
        System.out.println("notificationList = " + notificationList);
    }

    @Test
    public void timeCalc(){
        // 특정 시간 설정 예: 2023년 6월 25일 15시 30분
        LocalDateTime pastTime = LocalDateTime.of(2024, 6, 25, 13, 00);
        // 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        // 두 시간 사이의 차이 계산
        Duration duration = Duration.between(pastTime, now);
        // 차이를 분으로 변환
        long diffMinutes = duration.toMinutes();
        // 차이를 시간으로 변환
        long diffHours = duration.toHours();
        long diffDays = duration.toDays();
        // 결과 출력
        if( diffDays >0 ) {
            System.out.println("차이는 일로: " + diffDays + "일");
        }else if( diffHours >0 ){
            System.out.println("차이는 시간으로: " + diffHours + "시간");
        }else if( diffMinutes > 0) {
            System.out.println("차이는 시간으로: " + diffHours + "시간");
        }

    }
}

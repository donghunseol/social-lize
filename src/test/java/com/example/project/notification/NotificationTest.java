package com.example.project.notification;

import com.example.project.user.User;
import com.example.project.user.UserQueryRepository;
import com.example.project.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserQueryRepository.class)
@DataJpaTest
public class NotificationTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void test() {
//        User user = new User().builder().id(1).build();
//        User user = new User();
//        user.setId(1);
        Integer count = notificationRepository.getUnCheckedCountByUserId(1);
        System.out.println(count);
    }
}

package com.example.project.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        User user = userRepository.findByEmail("1@1.com");;
        
        List<User> userList = userRepository.findAll();
        System.out.println("userList = " + userList);
    }
}

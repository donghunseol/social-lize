package com.example.project.social;

import com.example.project.user.UserQueryRepository;
import com.example.project.user.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
@Import(UserQueryRepository.class)
@DataJpaTest
public class SocialRepositoryTest {

    @Autowired
    private SocialRepository socialRepository;

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Test
    public void test(){
        List<Object[]> mySocialList = userQueryRepository.mySocialList(1);
        List<UserResponse.MainDTO.MySocialDTO> mySocialList2 = mySocialList.stream().map(UserResponse.MainDTO.MySocialDTO::new).toList();
        System.out.println("mySocialList2 = " + mySocialList2);
    }
}

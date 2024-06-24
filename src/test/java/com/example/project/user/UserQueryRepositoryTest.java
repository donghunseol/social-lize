package com.example.project.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Objects;

@Import(UserQueryRepository.class)
@DataJpaTest
public class UserQueryRepositoryTest {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Test
    public void mySocialList_Test() {
        List<Object[]> list = userQueryRepository.mySocialList(1);

        for (Object[] row : list) {
            System.out.println(row[0]);
            System.out.println(row[1]);
            System.out.println(row[2]);
            System.out.println(row[3]);
        }
    }

    @Test
    public void mySocialPopularList_Test() {
        List<Object[]> list = userQueryRepository.mySocialPopularList(1);

        for (Object[] row : list) {
            System.out.println(row[0]);
            System.out.println(row[1]);
            System.out.println(row[2]);
            System.out.println(row[3]);
            System.out.println(row[4]);
        }
    }

    @Test
    public void popularPostList_Test() {
        List<Object[]> list = userQueryRepository.popularPostList();

        for (Object[] row : list) {
            System.out.println(row[0]);
            System.out.println(row[1]);
            System.out.println(row[2]);
            System.out.println(row[3]);
            System.out.println(row[4]);
        }
    }

    @Test
    public void categorySocialList_Test() {
        List<Object[]> list = userQueryRepository.categorySocialList(1);

        for (Object[] row : list) {
            System.out.println(row[0]);
            System.out.println(row[1]);
            System.out.println(row[2]);
            System.out.println(row[3]);
            System.out.println(row[4]);
            System.out.println(row[5]);
        }
    }
}

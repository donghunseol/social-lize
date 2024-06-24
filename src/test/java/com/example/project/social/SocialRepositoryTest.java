package com.example.project.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SocialRepositoryTest {

    @Autowired
    private SocialRepository socialRepository;
}

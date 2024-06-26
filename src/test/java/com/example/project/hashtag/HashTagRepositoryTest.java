package com.example.project.hashtag;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class HashTagRepositoryTest {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findBySocialId_test(){
        // given
        Integer socialId = 1;

        // when
        List<Hashtag> hashtags = hashtagRepository.findBySocialId(socialId);

        for (Hashtag hashtag : hashtags) {
            System.out.println(hashtag.getName());
        }
    }
}

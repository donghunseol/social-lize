package com.example.project.like;

import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class LikeRepositoryTest {

    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void findByLikeUserId_test(){
        Integer like = likeRepository.findByLikeUserId(4, 1);
        System.out.println(like);
    }
}

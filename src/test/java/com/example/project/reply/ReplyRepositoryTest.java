package com.example.project.reply;

import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void findBySocialId_test(){
        Integer re = replyRepository.replyCount(1);

        System.out.println(re);
    }
}

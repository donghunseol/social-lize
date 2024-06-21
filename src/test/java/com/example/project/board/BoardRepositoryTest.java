package com.example.project.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findBySocialId_test(){
        List<Board> board = boardRepository.findByBoardSocialId(1);

        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i).getUserId().getNickname());
        }
    }
}

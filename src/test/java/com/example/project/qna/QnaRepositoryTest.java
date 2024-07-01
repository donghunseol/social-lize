package com.example.project.qna;

import com.example.project._core.enums.QnaEnum;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class QnaRepositoryTest {

    @Autowired
    private QnaRepository qnaRepository;

    @Test
    public void findByQnaIdAndWaiting_test(){
        Qna qna = qnaRepository.findByQnaIdAndWaiting(6, QnaEnum.WAITING);
        System.out.println(qna);
    }

    @Test
    public void findByUserId_test(){
        List<Qna> qna = qnaRepository.findByUserId(1);
        System.out.println(qna.size());
    }
}

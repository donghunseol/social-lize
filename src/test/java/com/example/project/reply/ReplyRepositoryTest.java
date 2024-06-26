package com.example.project.reply;

import com.example.project.rereply.Rereply;
import com.example.project.rereply.RereplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private RereplyRepository rereplyRepository;

    @Test
    public void findBySocialId_test(){
        Integer re = replyRepository.replyCount(1);

        System.out.println(re);
    }

    @Test
    public void findByReply_test(){
        Integer boardId = 1;
        List<Reply> re = replyRepository.findByReply(boardId);

        for (int i = 0; i < re.size(); i++) {
            System.out.println(re.get(i).getComment());
        }
    }

    @Test
    public void rereplyRepository_test(){
        Integer replyId = 1;
        List<Rereply> re = rereplyRepository.findByReplyId(replyId);

        for (int i = 0; i < re.size(); i++) {
            System.out.println(re.get(i).getComment());
        }
    }
}

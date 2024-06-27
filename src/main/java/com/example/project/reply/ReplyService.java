package com.example.project.reply;

import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void delete(int replyId, int sessionUserId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new Exception404("없는 댓글을 삭제할 수 없어요"));

        if(reply.getUserId().getId() != sessionUserId){
            throw new Exception403("댓글을 삭제할 권한이 없어요");
        }

        replyRepository.deleteById(replyId);
    }
}

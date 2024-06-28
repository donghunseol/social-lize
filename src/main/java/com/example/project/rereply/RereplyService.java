package com.example.project.rereply;

import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.reply.Reply;
import com.example.project.reply.ReplyRepository;
import com.example.project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RereplyService {
    private final RereplyRepository rereplyRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void delete(int rereplyId, int sessionUserId) {
        Rereply rereply = rereplyRepository.findById(rereplyId)
                .orElseThrow(() -> new Exception404("없는 대댓글을 삭제할 수 없어요"));

        if (rereply.getUserId().getId() != sessionUserId) {
            throw new Exception403("대댓글을 삭제할 권한이 없어요");
        }

        rereplyRepository.delete(rereply);
    }

    @Transactional
    public void save(RereplyRequest.SaveDTO reqDTO, User sessionUser) {
        Reply reply = replyRepository.findById(reqDTO.getReplyId())
                .orElseThrow(() -> new Exception404("없는 댓글에 대댓글을 작성할 수 없어요"));

        Rereply rereply = reqDTO.toEntity(sessionUser, reply);

        rereplyRepository.save(rereply);
    }
}


package com.example.project.rereply;

import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.reply.Reply;
import com.example.project.reply.ReplyRepository;
import com.example.project.reply.ReplyRequest;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RereplyService {
    private final RereplyRepository rereplyRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Transactional
    public void delete(Integer rereplyId, Integer userId) {
        Rereply rereply = rereplyRepository.findById(rereplyId)
                .orElseThrow(() -> new Exception404("없는 대댓글을 삭제할 수 없어요"));

        if (!rereply.getUserId().getId().equals(userId)) {
            throw new Exception403("대댓글을 삭제할 권한이 없어요.");
        }

        rereplyRepository.delete(rereply);
    }

    @Transactional
    public void save(RereplyRequest.SaveDTO reqDTO, Integer userId, Integer boardId, Integer replyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        Reply parentReply = replyRepository.findById(replyId)
                .orElseThrow(() -> new Exception404("부모 댓글을 찾을 수 없습니다."));

        Rereply rereply = reqDTO.toEntity(user, parentReply);

        rereplyRepository.save(rereply);
    }
}


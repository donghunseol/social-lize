package com.example.project.reply;

import com.example.project._core.enums.ReplyEnum;
import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final SocialRepository socialRepository;

    @Transactional
    public void delete(Integer replyId, Integer sessionUserId) {
        String delete = "삭제된 댓글입니다.";
        Reply reply = replyRepository.findByActive(replyId);

        if(reply != null) {
            if(reply.getUserId().getId() != sessionUserId){
                throw new Exception403("댓글을 삭제할 권한이 없어요");
            }

            reply.setState(ReplyEnum.DELETE);
            reply.setComment(delete);
        }
    }

    @Transactional
    public void save(ReplyRequest.SaveDTO reqDTO, User sessionUser, Integer socialId) {
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("없는 게시글에 댓글을 작성할 수 없어요"));

        Board board = boardRepository.findById(social.getId())
                .orElseThrow(() -> new Exception404("관련된 게시글이 없어요"));

        Reply reply = reqDTO.toEntity(sessionUser, board);

        replyRepository.save(reply);
    }
}

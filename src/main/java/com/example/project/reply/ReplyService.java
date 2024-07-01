package com.example.project.reply;

import com.example.project._core.enums.ReplyEnum;
import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import com.example.project.social.SocialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final SocialRepository socialRepository;
    private final UserRepository userRepository;

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
    public void save(ReplyRequest.SaveDTO reqDTO, Integer userId, Integer boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("관련된 게시글이 없어요"));

        Reply reply = reqDTO.toEntity(user, board);

        replyRepository.save(reply);
    }

    public Integer getCount(Integer userId, Integer socialId) {
        return replyRepository.getCountByUserIdAndSocialId(userId, socialId);
    }
}

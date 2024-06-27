package com.example.project.reply;

import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import com.example.project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

//    @Transactional
//    public void delete(int replyId, int sessionUserId) {
//        Reply reply = replyRepository.findByUserAndBoard(replyId)
//                .orElseThrow(() -> new Exception404("없는 댓글을 삭제할 수 없어요"));
//
//        if(reply.getUserId().getId() != sessionUserId){
//            throw new Exception403("댓글을 삭제할 권한이 없어요");
//        }
//
//        replyRepository.delete(reply);
//    }

    @Transactional
    public void save(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardRepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("없는 게시글에 댓글을 작성할 수 없어요"));

        Reply reply = reqDTO.toEntity(sessionUser, board);

        replyRepository.save(reply);
    }
}

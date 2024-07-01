package com.example.project.like;

import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public boolean save(Integer boardId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        likeRepository.save(Like.builder().userId(user).boardId(board).build());
        return true;  // 성공 시 true 반환
    }

    @Transactional
    public boolean delete(Integer boardId, Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndBoard(userId, boardId)
                .orElseThrow(() -> new Exception404("좋아요가 없습니다."));

        likeRepository.deleteById(like.getId());

        return true;  // 성공 시 true 반환
    }
}

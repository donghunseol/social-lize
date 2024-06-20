package com.example.project.board;

import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final SocialRepository socialRepository;

    @Transactional
    public void save(Integer socialId, BoardRequest.SaveDTO reqDTO, Integer userId) {
        User user = userRepository.findById(userId).get();
        Social social = socialRepository.findById(socialId).get();


        Board board = boardRepository.save(reqDTO.toEntity(social, user));
    }
}

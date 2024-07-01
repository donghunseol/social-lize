package com.example.project.chat;

import com.example.project._core.errors.exception.Exception404;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final SocialRepository socialRepository;
    private final UserRepository userRepository;

    // 채팅 메시지 저장 메서드
    public ChatResponse.LiveChatDTO saveMessage(ChatRequest.LiveChatDTO reqDTO) {
        // 객체 조회
        Social social = socialRepository.findById(reqDTO.getSocialId())
                .orElseThrow(() -> new Exception404("소셜을 찾을 수 없습니다"));
        User user = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다"));

        // 받은 DTO를 기반으로 채팅 객체 생성
        Chat chat = Chat.builder()
                .socialId(social)
                .userId(user)
                .comment(reqDTO.getComment())
                .build();

        // 채팅 객체 DB 에 저장
        Chat savedChat = chatRepository.save(chat);

        return new ChatResponse.LiveChatDTO(
                savedChat.getId(),
                savedChat.getSocialId().getId(),
                savedChat.getUserId().getId(),
                savedChat.getComment(),
                savedChat.getCreatedAt()
        );
    }

    // 소셜 별 채팅 조회 메서드
    public List<ChatResponse.LiveChatDTO> getChatMessages(Integer socialId) {
        // 객체 조회
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("소셜을 찾을 수 없습니다"));

        // 해당 소셜 그룹 채팅 조회
        List<Chat> chats = chatRepository.findBySocialId(social.getId());

        // 조회된 채팅 메시지를 DTO 리스트로 변환하여 반환
        return chats.stream()
                .map(chat -> new ChatResponse.LiveChatDTO(
                        chat.getId(),
                        chat.getSocialId().getId(),
                        chat.getUserId().getId(),
                        chat.getComment(),
                        chat.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}

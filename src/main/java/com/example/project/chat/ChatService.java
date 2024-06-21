package com.example.project.chat;

import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final SocialRepository socialRepository;
    private final UserRepository userRepository;

    // 클라이언트로부터 받은 채팅 메시지를 데이터베이스에 저장하고, 저장된 메시지를 DTO로 반환
    public ChatResponse.LiveChatDTO saveMessage(ChatRequest.LiveChatDTO reqDTO) {
        reqDTO.setUserId(1);
        reqDTO.setSocialId(1);
        Social social = socialRepository.findById(reqDTO.getSocialId())
                .orElseThrow(() -> new Exception404("존재하지 않는 소셜입니다"));
        User sessionUser = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception401("존재하지 않는 유저입니다"));

        Chat chat = new Chat();
        chat.setSocialId(social); // Social 엔티티 생성자로 설정
        chat.setUserId(sessionUser); // User 엔티티 생성자로 설정
        chat.setComment(reqDTO.getComment());
        chat.setCreatedAt(LocalDateTime.now());
        chatRepository.save(chat);

        return null;
    }

    // 데이터베이스에 저장된 모든 채팅 메시지를 가져와 DTO 리스트로 반환
    public List<ChatResponse.LiveChatDTO> getAllMessages() {
        return chatRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // DTO로 변환
    private ChatResponse.LiveChatDTO convertToResponseDTO(Chat chat) {
        return new ChatResponse.LiveChatDTO(
                chat.getId(),
                chat.getSocialId().getId(), // Social 엔티티의 ID를 가져옴
                chat.getUserId().getId(), // User 엔티티의 ID를 가져옴
                chat.getComment(),
                chat.getCreatedAt()
        );
    }
}

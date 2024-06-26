package com.example.project.chat;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    private final HttpSession session;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{socialId}")
    public void sendMessage(@DestinationVariable Integer socialId, ChatRequest.LiveChatDTO reqDTO) {
        System.out.println("응답 메시지 : " + reqDTO.getComment());
        ChatResponse.LiveChatDTO response = chatService.saveMessage(reqDTO);
        messagingTemplate.convertAndSend("/topic/chat/" + socialId, response);
    }

    @GetMapping("/chat/list/{socialId}")
    @ResponseBody
    public List<ChatResponse.LiveChatDTO> getMessages(@PathVariable Integer socialId) {
        return chatService.getChatMessages(socialId);
    }

    @GetMapping("/test/chat/{socialId}")
    public String testChat(@PathVariable Integer socialId) {
        socialId = 1;
        return "test";
    }
}
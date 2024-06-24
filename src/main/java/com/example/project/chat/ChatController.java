package com.example.project.chat;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    private final HttpSession session;


    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatResponse.LiveChatDTO sendMessage(ChatRequest.LiveChatDTO messageDTO) {
        return chatService.saveMessage(messageDTO);
    }

    @GetMapping("/chat/{socialId}")
    @ResponseBody
    public List<ChatResponse.LiveChatDTO> getMessages(@PathVariable Integer socialId) {
        return chatService.getChatMessages(socialId);
    }

    @GetMapping("/chat/test")
    public String test() {
        return "social/fileaddForm";
    }
}

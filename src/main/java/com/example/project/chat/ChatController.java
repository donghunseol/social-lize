package com.example.project.chat;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    private final HttpSession session;

    @PostMapping("/send")
    public ChatResponse.LiveChatDTO sendMessage(ChatRequest.LiveChatDTO reqDTO) {
        return chatService.saveMessage(reqDTO);
    }

    @GetMapping("/messages")
    public List<ChatResponse.LiveChatDTO> getMessages() {
        return chatService.getAllMessages();
    }

    @GetMapping("/chat/test")
    public String test() {
        return "social/fileaddForm";
    }
}

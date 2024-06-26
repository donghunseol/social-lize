package com.example.project.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatRestController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/receive-data")
    public String receiveData(@RequestBody ChatRequest.LiveChatDTO reqDTO) {
        System.out.println("Received data: " + reqDTO.getComment());
        messagingTemplate.convertAndSend("/topic/chat", reqDTO);
        return "Data received successfully";
    }
}

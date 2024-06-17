package com.example.project.chat;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "chat_tb")
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 채팅 번호

    @Builder
    public Chat(Integer id) {
        this.id = id;
    }
}



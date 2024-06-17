package com.example.project.reply;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "reply_tb")
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 댓글 번호

    @Builder
    public Reply(Integer id) {
        this.id = id;

    }
}



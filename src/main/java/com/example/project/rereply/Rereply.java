package com.example.project.rereply;

import com.example.project.reply.Reply;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "rereply_tb")
@Entity
public class Rereply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 대댓글 번호

    @JoinColumn(name = "replyId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reply replyId; // 댓글 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @Column(nullable = false)
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Rereply(Integer id, Reply replyId, User userId, String comment) {
        this.id = id;
        this.replyId = replyId;
        this.userId = userId;
        this.comment = comment;
    }
}
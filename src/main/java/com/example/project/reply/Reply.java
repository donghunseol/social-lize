package com.example.project.reply;

import com.example.project.board.Board;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "reply_tb")
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 댓글 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardId; // 게시글 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호


    @Column(nullable = false)
    private String comment; // 댓글 내용

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Reply(Integer id, User userId, Board boardId, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}



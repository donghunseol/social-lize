package com.example.project.reply;

import com.example.project._core.enums.QnaEnum;
import com.example.project._core.enums.ReplyEnum;
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

    @ManyToOne
    @JoinColumn(name = "parent_reply_id")
    private Reply parentReply; // 부모 댓글 ID

    @Column(nullable = false)
    private String comment; // 댓글 내용

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReplyEnum state; // 활성화, 댓글 삭제

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자


    @Builder
    public Reply(Integer id, Board boardId, User userId, Reply parentReply, String comment, ReplyEnum state, LocalDateTime createdAt) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.parentReply = parentReply;
        this.comment = comment;
        this.state = state;
        this.createdAt = createdAt;
    }
}



package com.example.project.report;

import com.example.project._core.enums.ReportResultEnum;
import com.example.project.board.Board;
import com.example.project.reply.Reply;
import com.example.project.rereply.Rereply;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "report_tb")
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 신고 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 신고 당한 유저 번호

    @Column(nullable = false)
    private Integer reportUserId; // 신고한 유저 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardId; // 게시글 번호

    @JoinColumn(name = "replyId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reply replyId; // 댓글 번호

    @JoinColumn(name = "rereplyId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rereply rereplyId; // 대댓글 번호

    @Column(nullable = false)
    private String content; // 신고 사유

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ReportResultEnum result; // 처리 결과

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Report(Integer id, User userId, Integer reportUserId, Board boardId, Reply replyId, Rereply rereplyId, String content, ReportResultEnum result, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.reportUserId = reportUserId;
        this.boardId = boardId;
        this.replyId = replyId;
        this.rereplyId = rereplyId;
        this.content = content;
        this.result = result;
        this.createdAt = createdAt;
    }
}



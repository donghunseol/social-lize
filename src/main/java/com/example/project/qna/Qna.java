package com.example.project.qna;

import com.example.project._core.enums.QnaEnum;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "qna_tb")
@Entity
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 문의사항 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @Column(nullable = false)
    private String title; // 문의 사항 제목

    @Column(nullable = false)
    private String content; // 문의 사항 내용

    private String replyContent; // 문의 사항 관리자 답변

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QnaEnum state; // 처리완료, 대기중, 문의 삭제

    private LocalDateTime replyCreatedAt; // 답변 일자

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Qna(Integer id, User userId, String title, String content, String replyContent, QnaEnum state, LocalDateTime replyCreatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.state = state;
        this.replyContent = replyContent;
        this.replyCreatedAt = replyCreatedAt;
        this.createdAt = createdAt;
    }
}



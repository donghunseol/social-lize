package com.example.project.file;

import com.example.project.board.Board;
import com.example.project.social.Social;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "file_tb")
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 첨부 파일 번호

    @JoinColumn(name = "socialId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Social socialId; // 소셜 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardId; // 게시글 번호

    @Column(nullable = false)
    private String path; // 첨부파일 경로

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public File(Integer id, Social socialId, User userId, Board boardId, String path, LocalDateTime createdAt) {
        this.id = id;
        this.socialId = socialId;
        this.userId = userId;
        this.boardId = boardId;
        this.path = path;
        this.createdAt = createdAt;
    }
}



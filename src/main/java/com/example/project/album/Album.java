package com.example.project.album;

import com.example.project._core.enums.AlbumEnum;
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
@Table(name = "album_tb")
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 이미지 및 영상 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardId; // 게시글 번호

    @Column(nullable = false)
    private String path; // 이미지 경로

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AlbumEnum type; // 이미지 또는 영상 타입

    private String hlsPath; // HLS 변환된 파일의 경로

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Album(Integer id, User userId, Board boardId, String path, AlbumEnum type, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.path = path;
        this.type = type;
        this.createdAt = createdAt;
    }
}



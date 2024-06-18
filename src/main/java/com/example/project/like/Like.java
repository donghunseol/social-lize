package com.example.project.like;

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
@Table(name = "like_tb")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 좋아요 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardId; // 게시글 번호

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Like(Integer id) {
        this.id = id;

    }
}



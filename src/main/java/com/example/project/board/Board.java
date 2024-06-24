package com.example.project.board;

import com.example.project._core.enums.BoardEnum;
import com.example.project.reply.Reply;
import com.example.project.social.Social;
import com.example.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 게시글 번호

    @JoinColumn(name = "socialId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Social socialId; // 소셜 번호

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId; // 유저 번호

    @Column(nullable = false)
    private String content; // 게시글 내용

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardEnum role; // 공지와 구별

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @OrderBy("id desc")
    @OneToMany(mappedBy = "boardId", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Board(Integer id, Social socialId, User userId, String title, String content, BoardEnum role, LocalDateTime createdAt) {
        this.id = id;
        this.socialId = socialId;
        this.userId = userId;
        this.content = content;
        this.role = role;
        this.createdAt = createdAt;
    }
}



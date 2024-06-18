package com.example.project.hashtag;

import com.example.project.board.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "hashtagk_tb")
@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 게시글 해시태그 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardId; // 게시글 번호

    @Column(nullable = false)
    private String name; // 해쉬태그 이름

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Hashtag(Integer id) {
        this.id = id;

    }
}



package com.example.project.notice;

import com.example.project.board.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "notice_tb")
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 공지사항 번호

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board; // 게시글 번호

    @Builder
    public Notice(Integer id, Board board) {
        this.id = id;
        this.board = board;
    }
}



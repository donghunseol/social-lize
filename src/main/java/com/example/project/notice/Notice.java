package com.example.project.notice;

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

    @Builder
    public Notice(Integer id) {
        this.id = id;
    }
}



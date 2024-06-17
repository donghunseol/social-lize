package com.example.project.hashtag;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "hashtagk_tb")
@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 게시글 해시태그 번호

    @Builder
    public Hashtag(Integer id) {
        this.id = id;

    }
}



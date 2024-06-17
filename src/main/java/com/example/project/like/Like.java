package com.example.project.like;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "like_tb")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 좋아요 번호

    @Builder
    public Like(Integer id) {
        this.id = id;

    }
}



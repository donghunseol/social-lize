package com.example.project.rereply;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "rereply_tb")
@Entity
public class Rereply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 대댓글 번호

    @Builder
    public Rereply(Integer id) {
        this.id = id;

    }
}



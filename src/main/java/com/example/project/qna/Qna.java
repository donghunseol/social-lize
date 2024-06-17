package com.example.project.qna;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "qna_tb")
@Entity
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 문의사항 번호

    @Builder
    public Qna(Integer id) {
        this.id = id;
    }
}



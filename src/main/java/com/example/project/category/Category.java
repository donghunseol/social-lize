package com.example.project.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "category_tb")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 소셜 카테고리 번호

    @Builder
    public Category(Integer id) {
        this.id = id;

    }
}



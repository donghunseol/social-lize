package com.example.project.category;

import com.example.project.category_name.CategoryName;
import com.example.project.social.Social;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "category_tb")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 소셜 카테고리 번호

    @JoinColumn(name = "socialId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Social socialId; // 소셜 번호

    @JoinColumn(name = "categoryNameId")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryName categoryNameId; // 카테고리 네임 번호

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Category(Integer id, Social socialId, CategoryName categoryNameId, LocalDateTime createdAt) {
        this.id = id;
        this.socialId = socialId;
        this.categoryNameId = categoryNameId;
        this.createdAt = createdAt;
    }
}



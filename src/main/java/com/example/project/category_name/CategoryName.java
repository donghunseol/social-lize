package com.example.project.category_name;

import com.example.project._core.enums.CategoryNameStateEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "category_name_tb")
@Entity
public class CategoryName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 카테고리 네임 번호

    private String name; // 카테고리 네임

    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryNameStateEnum status; // 카테고리 삭제 유무

    @Builder
    public CategoryName(Integer id, String name, String imagePath, CategoryNameStateEnum status) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.status = status;
    }
}

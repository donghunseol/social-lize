package com.example.project.social;

import com.example.project._core.enums.SocialStateEnum;
import com.example.project.category.Category;
import com.example.project.social_member.SocialMember;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "social_tb")
@Entity
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 소셜 번호

    @Column(nullable = false)
    private String name; // 소셜 이름

    @Column(nullable = false)
    private String image; // 소셜 사진

    @Column(nullable = false)
    private String info; // 소셜 소개글

    @OneToMany(mappedBy = "socialId", cascade = CascadeType.ALL)
    private List<Category> category; // 카테고리

    @OneToMany(mappedBy = "socialId", cascade = CascadeType.ALL)
    private List<SocialMember> SocialMember; // 카테고리

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialStateEnum status; // 소셜 상태

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 일자

    @Builder
    public Social(Integer id, String name, String image, String info, List<Category> category, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.info = info;
        this.category = category;
        this.createdAt = createdAt;
    }
}

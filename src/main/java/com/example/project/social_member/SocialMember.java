package com.example.project.social_member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "social_member_tb")
@Entity
public class SocialMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 소셜 멤버 번호

    @Builder
    public SocialMember(Integer id) {
        this.id = id;
    }
}



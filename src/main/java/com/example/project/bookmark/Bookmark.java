package com.example.project.bookmark;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "bookmark_tb")
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 북마크 번호

    @Builder
    public Bookmark(Integer id) {
        this.id = id;

    }
}



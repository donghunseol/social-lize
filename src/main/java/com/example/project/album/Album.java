package com.example.project.album;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "album_tb")
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 이미지 및 영상 번호

    @Builder
    public Album(Integer id) {
        this.id = id;

    }
}



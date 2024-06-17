package com.example.project.file;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "file_tb")
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 첨부 파일 번호

    @Builder
    public File(Integer id) {
        this.id = id;

    }
}



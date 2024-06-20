package com.example.project.file;

import com.example.project.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {

    // 소셜 별 파일 리스트 출력
    @Query("""
        SELECT f FROM File f
        JOIN FETCH f.boardId b
        JOIN FETCH f.boardId.socialId s
        WHERE f.boardId.socialId.id = :socialId
    """)
    List<File> findBySocialId(@Param("socialId") Integer socialId);
}

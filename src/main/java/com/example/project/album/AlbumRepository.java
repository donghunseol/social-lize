package com.example.project.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    // 소셜 별 이미지 및 영상 리스트 출력
    @Query("""
        SELECT a FROM Album a
        JOIN FETCH a.boardId b
        JOIN FETCH a.boardId.socialId s
        WHERE a.boardId.socialId.id = :socialId
    """)
    List<Album> findBySocialId(@Param("socialId") Integer socialId);

    @Query("select a from Album a where a.boardId.id = :boardId")
    List<Album> findByBoardId(@Param("boardId") Integer boardId);
}

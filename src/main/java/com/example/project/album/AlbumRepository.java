package com.example.project.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("""
        SELECT a FROM Album a
        JOIN FETCH a.boardId b
        JOIN FETCH a.boardId.socialId s
        WHERE a.boardId.socialId.id = :socialId
    """)
    List<Album> findBySocialId(@Param("socialId") Integer socialId);
}

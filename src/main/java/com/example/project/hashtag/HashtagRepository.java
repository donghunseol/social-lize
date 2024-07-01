package com.example.project.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

    @Query("select h from Hashtag h where h.boardId.id = :boardId")
    List<Hashtag> findByBoardId(@Param("boardId") Integer boardId);

    @Query("select h from Hashtag h where h.boardId.socialId.id = :socialId")
    List<Hashtag> findBySocialId(@Param("socialId") Integer socialId);
}

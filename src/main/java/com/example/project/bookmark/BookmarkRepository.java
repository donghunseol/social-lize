package com.example.project.bookmark;

import com.example.project.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    @Query("select count(b) from Bookmark b where b.boardId.id = :boardId and b.userId.id = :userId")
    Integer findByBookUserId(@Param("boardId") Integer boardId, @Param("userId") Integer userId);

    @Query("select b from Bookmark b where b.userId.id = :userId and b.boardId.id = :boardId")
    Optional<Bookmark> findByUserAndBoard(@Param("userId") Integer userId, @Param("boardId") Integer boardId);
}

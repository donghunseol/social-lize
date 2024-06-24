package com.example.project.chat;

import com.example.project.social.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where c.socialId.id = :socialId")
    List<Chat> findBySocialId(@Param("socialId") Integer socialId);
}

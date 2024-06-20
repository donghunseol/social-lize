package com.example.project.social_member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SocialMemberRepository extends JpaRepository<SocialMember, Integer> {

    @Query("""
        SELECT sm FROM SocialMember sm
        WHERE sm.socialId.id = :socialId
        AND sm.userId.id = :userId
    """)
    Optional<SocialMember> findByUserIdAndSocialId(@Param("userId") Integer userId, @Param("socialId") Integer socialId);
}

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

    // 소셜 멤버 중 APPROVED 인 멤버 수
    @Query("SELECT COUNT(sm) FROM SocialMember sm WHERE sm.socialId.id = :socialId AND sm.state = 'APPROVED'")
    int countBySocialId(@Param("socialId") Integer socialId);

    // 소셜 멤버 중 APPROVED 인 멤버 수
    @Query("SELECT CASE WHEN COUNT(sm) > 0 THEN TRUE ELSE FALSE END FROM SocialMember sm WHERE sm.socialId.id = :socialId AND sm.userId.id = :userId AND sm.state = 'APPROVED'")
    Boolean isApproved(@Param("socialId") Integer socialId, @Param("userId") Integer userId);

}

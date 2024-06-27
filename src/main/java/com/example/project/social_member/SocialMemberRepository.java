package com.example.project.social_member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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

    // 소셜 멤버 중 APPROVED 인 멤버
    @Query("SELECT CASE WHEN COUNT(sm) > 0 THEN TRUE ELSE FALSE END FROM SocialMember sm WHERE sm.socialId.id = :socialId AND sm.userId.id = :userId AND sm.state = 'APPROVED'")
    Boolean isApproved(@Param("socialId") Integer socialId, @Param("userId") Integer userId);

    // 소셜 멤버 중 APPROVED 인 멤버 리스트
    @Query("SELECT new com.example.project.social_member.SocialMemberResponse$SocialMemberList(s.id, u.nickname, u.email, u.birth, u.provider, u.image, u.createdAt, sm.role) " +
            "FROM SocialMember sm " +
            "JOIN sm.socialId s " +
            "JOIN sm.userId u " +
            "WHERE sm.state = 'APPROVED' AND s.id = :socialId " +
            "ORDER BY u.nickname ASC")
    List<SocialMemberResponse.SocialMemberList> findSocialMembersBySocialId(@Param("socialId") Integer socialId);

    @Query("select sm from SocialMember sm where sm.socialId.id = :socialId and sm.role = 'MANAGER'")
    SocialMember findBySocialId(@Param("socialId") Integer socialId);

    @Query("select sm from SocialMember sm where sm.socialId.id = :socialId and sm.userId.id = :userId and sm.role = 'MANAGER'")
    SocialMember findByManager(@Param("socialId") Integer socialId, @Param("userId") Integer userId);

    @Query("select sm from SocialMember sm where sm.socialId.id = :socialId and sm.state = 'WAITING'")
    List<SocialMember> findByWaiting(@Param("socialId") Integer socialId);

    @Query("select count(sm) > 0 from SocialMember sm where sm.socialId.id = :socialId and sm.userId.id = :userId and sm.state = 'WAITING'")
    Boolean findByUserWaiting(@Param("socialId") Integer socialId, @Param("userId") Integer userId);
}

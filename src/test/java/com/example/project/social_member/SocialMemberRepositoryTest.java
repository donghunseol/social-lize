package com.example.project.social_member;

import com.example.project._core.errors.exception.Exception401;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SocialMemberRepositoryTest {

    @Autowired
    private SocialMemberRepository socialMemberRepository;

    @Test
    public void findByUserIdAndSocialId_test(){
        // given
        Integer userId = 1;
        Integer inSocialId = 1;
        Integer outSocialId = 4;

        // when
        SocialMember inSocialMember = socialMemberRepository.findByUserIdAndSocialId(userId, inSocialId)
                .orElseThrow(() -> new Exception401("해당 소셜의 멤버가 아닙니다"));
        System.out.println("inSocialMember/test/id : " + inSocialMember.getUserId().getId());

        SocialMember outSocialMember = socialMemberRepository.findByUserIdAndSocialId(userId, outSocialId)
                .orElseThrow(() -> new Exception401("해당 소셜의 멤버가 아닙니다"));
        System.out.println("outSocialMember/test/id : " +outSocialMember.getUserId().getId());

        // eye

        // then

    }
}

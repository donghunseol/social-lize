package com.example.project.social_member;

import com.example.project._core.errors.exception.Exception401;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class SocialMemberRepositoryTest {

    @Autowired
    private SocialMemberRepository socialMemberRepository;

    @Test
    public void getSocialMemberBySocialId() {
        List<SocialMember> socialMemberList = socialMemberRepository.findSocialMembersBySocialId(1);
        List<SocialMemberResponse.SocialMemberDTO> socialMemberDTOList = new ArrayList<>();
        for (SocialMember socialMember : socialMemberList) {
            socialMemberDTOList.add( new SocialMemberResponse.SocialMemberDTO(socialMember) );
        }
//        socialMemberList.stream().map(SocialMember::getSocialId).forEach(System.out::println);
//        System.out.println("socialMemberList = " + socialMemberList);
    }

    @Test
    public void findByUserIdAndSocialId_test() {
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
        System.out.println("outSocialMember/test/id : " + outSocialMember.getUserId().getId());

        // eye

        // then

    }

    @Test
    public void findByApproved_test(){
        // given
        Integer userId = 1;
        Integer socialId = 1;

        // when
        Boolean inSocialMember = socialMemberRepository.isApproved(socialId, userId);
        System.out.println(inSocialMember);

        // eye

        // then
    }

    @Test
    public void findBySocialId_test(){
        // given
        Integer socialId = 1;

        // when
        SocialMember sm = socialMemberRepository.findBySocialId(socialId);
        System.out.println(sm.getUserId().getNickname());

        // eye

        // then
    }

    @Test
    public void findByWaiting_test() {
        // given
        int socialId = 1;

        // when
        List<SocialMember> socialMemberList = socialMemberRepository.findByWaiting(socialId);

        // eye
        for (SocialMember sm : socialMemberList) {
            System.out.println(sm.getUserId().getNickname());
        }

        // then

    }

    @Test
    public void findByManager_test() {
        // given
        int socialId = 2;
        int userId = 1;

        // when
        SocialMember socialMember = socialMemberRepository.findByManager(socialId, userId);

        // eye
        System.out.println(socialMember);

        // then

    }
}

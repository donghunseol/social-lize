package com.example.project.social_member;

import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.SocialMemberStateEnum;
import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.BoardRepository;
import com.example.project.reply.ReplyRepository;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SocialMemberService {
    private final SocialMemberRepository socialMemberRepository;
    private final SocialRepository socialRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // 소셜 가입 신청
    @Transactional
    public void applySocialMember(Integer socialId, Integer userId) {
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("해당 소셜을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));

        // 소셜 멤버 여부 및 상태 확인
        Optional<SocialMember> MemberCheck = socialMemberRepository.findByUserIdAndSocialId(user.getId(), social.getId());

        if (MemberCheck.isPresent()) {
            SocialMember socialMember = MemberCheck.get();
            SocialMemberStateEnum state = socialMember.getState();
            if (state == SocialMemberStateEnum.WAITING || state == SocialMemberStateEnum.APPROVED) {
                throw new Exception400("해당 유저는 이미 가입 또는 신청 대기 상태입니다.");
            } else if (state == SocialMemberStateEnum.REFUSE || state == SocialMemberStateEnum.RESIGN) {
                // 기존 멤버 상태를 WAITING으로 변경
                socialMember.setState(SocialMemberStateEnum.WAITING);
                socialMemberRepository.save(socialMember);
                return;
            }
        }

        // 소셜 멤버 생성 및 저장
        SocialMember socialMember = SocialMember.builder()
                .socialId(social)
                .userId(user)
                .role(SocialMemberRoleEnum.MEMBER) // 기본 권한 설정
                .state(SocialMemberStateEnum.WAITING) // 가입 대기 상태로 설정
                .build();

        socialMemberRepository.save(socialMember);
    }

    // 소셜 가입 승인, 거절
    @Transactional
    public void updateSocialMemberState(Integer userId, Integer sessionUserId, Integer socialId, String isApproved) {
        SocialMember socialMember = socialMemberRepository.findByUserIdAndSocialId(userId, socialId)
                .orElseThrow(() -> new Exception404("해당 소셜 멤버를 찾을 수 없습니다."));

        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다."));

        // 현재 요청하는 유저가 소셜의 마스터인지 확인
        SocialMember masterMember = socialMemberRepository.findByUserIdAndSocialId(user.getId(), socialId)
                .orElseThrow(() -> new Exception401("해당 소셜의 멤버가 아닙니다."));

        if (masterMember.getRole() != SocialMemberRoleEnum.MANAGER) {
            throw new Exception403("멤버 가입 승인 및 거절은 소셜 마스터만 가능합니다.");
        }

        if (isApproved.equals("false")) {
            socialMember.setState(SocialMemberStateEnum.REFUSE);
        } else if (isApproved.equals("true")) {
            socialMember.setState(SocialMemberStateEnum.APPROVED);
        }

        socialMemberRepository.save(socialMember);
    }

    public SocialMemberResponse.MemberDTO member(Integer userId, Integer socialId) {
        Boolean isManager = true;


        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("소셜을 찾을 수 없습니다."));

        SocialMember socialMember = socialMemberRepository.findBySocialId(socialId);

        Integer socialMemberCount = socialMemberRepository.countBySocialId(socialId);

        SocialMember manager = socialMemberRepository.findByManager(socialId, userId);

        List<SocialMember> waiting = socialMemberRepository.findByWaiting(socialId);

        if (manager == null) {
            isManager = false;
        }

        return new SocialMemberResponse.MemberDTO(social, socialMember.getUserId().getNickname(), socialMemberCount, isManager, waiting);
    }

    //사용자가 소셜에 작성한 글과 댓글 갯수 가져오기
    public SocialMemberResponse.ArticleCount getArticleCount(Integer userId, Integer socialId) {
        return new SocialMemberResponse.ArticleCount(
                boardRepository.getArticleCountByBoardSocialIdAndUserId(userId, socialId),
                replyRepository.getCountByUserIdAndSocialId(userId, socialId)
        );
    }
}

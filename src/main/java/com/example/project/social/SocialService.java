package com.example.project.social;

import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.SocialMemberStateEnum;
import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.category.Category;
import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import com.example.project.social_member.SocialMember;
import com.example.project.social_member.SocialMemberRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SocialService {
    private final SocialRepository socialRepository;
    private final CategoryNameRepository categoryNameRepository;
    private final UserRepository userRepository;
    private final SocialMemberRepository socialMemberRepository;

    // 새로운 소셜 생성
    // TODO 유저 확인 세션으로 수정해야 함
    @Transactional
    public void createSocial(SocialRequest.Create CreateDTO) {
        // 소셜명 중복 체크
        Optional<Social> SocialNameCheck = socialRepository.findByName(CreateDTO.getName());
        if (SocialNameCheck.isPresent()) {
            throw new Exception400("입력하신 소셜명은 중복된 소셜명입니다.");
        }

        // 새로운 소셜 생성
        Social social = Social.builder()
                .name(CreateDTO.getName())
                .image(CreateDTO.getImage())
                .info(CreateDTO.getInfo())
                .build();

        // 카테고리 등록
        List<Category> categories = CreateDTO.getCategories().stream().map(categoryDTO -> {
            // 카테고리 유무 확인
            CategoryName categoryName = categoryNameRepository.findById(categoryDTO.getCategoryNameId())
                    .orElseThrow(() -> new Exception404("선택하신 카테고리 명을 찾을 수 없습니다."));
            return Category.builder()
                    .socialId(social)
                    .categoryNameId(categoryName)
                    .build();
        }).collect(Collectors.toList());

        social.setCategory(categories);
        Social saveSocial = socialRepository.save(social);

        // 유저 확인
        User user = userRepository.findById(CreateDTO.getUserId())
                .orElseThrow(() -> new Exception401("존재하지 않는 계정입니다."));

        // 소셜 멤버 등록 및 권한 부여
        SocialMember socialMember = SocialMember.builder()
                .socialId(saveSocial)
                .userId(user)
                .role(SocialMemberRoleEnum.MEMBER)
                .state(SocialMemberStateEnum.APPROVED)
                .build();

        socialMemberRepository.save(socialMember);
    }
}

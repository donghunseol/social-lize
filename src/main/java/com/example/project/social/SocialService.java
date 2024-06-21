package com.example.project.social;

import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.SocialMemberStateEnum;
import com.example.project._core.enums.SocialStateEnum;
import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.album.Album;
import com.example.project.album.AlbumRepository;
import com.example.project.category.Category;
import com.example.project.category.CategoryRepository;
import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import com.example.project.file.File;
import com.example.project.file.FileRepository;
import com.example.project.social_member.SocialMember;
import com.example.project.social_member.SocialMemberRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SocialService {
    private final SocialRepository socialRepository;
    private final CategoryNameRepository categoryNameRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SocialMemberRepository socialMemberRepository;
    private final AlbumRepository albumRepository;
    private final FileRepository fileRepository;

    // 새로운 소셜 생성
    // TODO 유저 확인 세션으로 수정해야 함
    @Transactional
    public void createSocial(SocialRequest.Create createDTO) {
        // 소셜명 중복 체크
        Optional<Social> SocialNameCheck = socialRepository.findByName(createDTO.getName());
        if (SocialNameCheck.isPresent()) {
            throw new Exception400("해당 소셜명은 이미 존재하는 소셜명입니다.");
        }

        // 새로운 소셜 생성
        Social social = Social.builder()
                .name(createDTO.getName())
                .image(createDTO.getImage())
                .info(createDTO.getInfo())
                .build();

        // 카테고리 등록
        List<Category> categories = createDTO.getCategories().stream().map(categoryDTO -> {
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
        User user = userRepository.findById(createDTO.getUserId())
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

    // 소셜 정보 수정
    @Transactional
    public void updateSocial(Integer socialId, SocialRequest.Update updateDTO) {
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("해당 소셜은 존재하지 않습니다."));

        // 소셜명 중복 체크 (자기 자신 제외)
        Optional<Social> SocialNameCheck = socialRepository.findByName(updateDTO.getName());
        if (SocialNameCheck.isPresent() && !SocialNameCheck.get().getId().equals(social.getId())) {
            throw new Exception400("해당 소셜명은 이미 존재하는 소셜명입니다.");
        }

        // 소셜 업데이트
        social.setName(updateDTO.getName());
        social.setImage(updateDTO.getImage());
        social.setInfo(updateDTO.getInfo());

        // 기존 카테고리 삭제
        categoryRepository.deleteAll(social.getCategory());

        // 새 카테고리 추가
        List<Category> categories = updateDTO.getCategories().stream().map(categoryDTO -> {
            CategoryName categoryName = categoryNameRepository.findById(categoryDTO.getCategoryNameId())
                    .orElseThrow(() -> new Exception404("선택하신 카테고리가 존재하지 않습니다."));
            return Category.builder()
                    .socialId(social)
                    .categoryNameId(categoryName)
                    .build();
        }).collect(Collectors.toList());

        social.setCategory(categories);

        socialRepository.save(social);
    }

    // 소셜 삭제
    @Transactional
    public void deleteSocial(Integer id) {
        Social social = socialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 소셜은 존재하지 않습니다."));

        // 상태를 DELETED로 변경
        social.setStatus(SocialStateEnum.DELETED);
        socialRepository.save(social);
    }

    // 소셜 별 앨범, 파일 리스트 출력
    public SocialResponse.AlbumAndFileListDTO getSocialAlbumList(Integer socialId) {

        // 소셜 별 앨범 리스트 가져오기
        List<Album> albumList = albumRepository.findBySocialId(socialId);
        List<File> fileList = fileRepository.findBySocialId(socialId);

        // 소셜에 앨범이 비었을 때 null 을 반환
        if (albumList == null) {
            albumList = Collections.emptyList();
        }

        // 소셜에 파일이 비었을 때 null 을 반환
        if (fileList == null) {
            fileList = Collections.emptyList();
        }

        // 앨범, 파일 리스트 DTO 담기
        return new SocialResponse.AlbumAndFileListDTO(socialId, albumList, fileList);
    }
}

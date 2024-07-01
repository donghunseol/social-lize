package com.example.project.social;

import com.example.project._core.enums.DeleteStateEnum;
import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.SocialMemberStateEnum;
import com.example.project._core.enums.SocialStateEnum;
import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project._core.utils.FileUtil;
import com.example.project._core.utils.ImageVideoUtil;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.album.Album;
import com.example.project.album.AlbumRepository;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import com.example.project.board.BoardResponse;
import com.example.project.bookmark.BookmarkRepository;
import com.example.project.category.Category;
import com.example.project.category.CategoryRepository;
import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import com.example.project.file.File;
import com.example.project.file.FileRepository;
import com.example.project.hashtag.Hashtag;
import com.example.project.hashtag.HashtagRepository;
import com.example.project.like.LikeRepository;
import com.example.project.reply.Reply;
import com.example.project.reply.ReplyRepository;
import com.example.project.social_member.SocialMember;
import com.example.project.social_member.SocialMemberRepository;
import com.example.project.social_member.SocialMemberResponse;
import com.example.project.user.User;
import com.example.project.user.UserQueryRepository;
import com.example.project.user.UserRepository;
import com.example.project.user.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final ReplyRepository replyRepository;
    private final BookmarkRepository bookRepository;
    private final HashtagRepository hashtagRepository;
    private final UserQueryRepository userQueryRepository;

    public BoardResponse.SocialDetailDTO socialDetail(int socialId, Integer userId) {
        Map<String, String> dayNameMap = new HashMap<>();
        dayNameMap.put("Sunday", "일");
        dayNameMap.put("Monday", "월");
        dayNameMap.put("Tuesday", "화");
        dayNameMap.put("Wednesday", "수");
        dayNameMap.put("Thursday", "목");
        dayNameMap.put("Friday", "금");
        dayNameMap.put("Saturday", "토");

        Map<String, Integer> countsByDay = new LinkedHashMap<>();
        countsByDay.put("일", 0);
        countsByDay.put("월", 0);
        countsByDay.put("화", 0);
        countsByDay.put("수", 0);
        countsByDay.put("목", 0);
        countsByDay.put("금", 0);
        countsByDay.put("토", 0);


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        List<Board> boards = boardRepository.findByBoardSocialId(socialId);

        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("소셜을 찾을 수 없습니다."));

        List<Hashtag> hashtag = hashtagRepository.findBySocialId(socialId);

        SocialMember socialMember = socialMemberRepository.findBySocialId(socialId);

        Integer socialMemberCount = socialMemberRepository.countBySocialId(socialId);

        List<Object[]> week = boardRepository.findPostCountsByDayOfWeek(socialId);


        Boolean isWaiting = socialMemberRepository.findByUserWaiting(socialId, userId);

        // 쿼리 결과 반영 및 요일 이름 변환
        for (Object[] result : week) {
            String dayOfWeek = (String) result[0];
            Integer count = ((Number) result[1]).intValue();
            String koreanDay = dayNameMap.get(dayOfWeek);
            countsByDay.put(koreanDay, count);
        }

        // 결과를 List<Integer>로 변환
        List<Integer> finalResults = new ArrayList<>(countsByDay.values());

        List<BoardResponse.SocialDetailDTO.BoardDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            Integer likeCount = likeRepository.findByLikeCount(board.getId());
            Integer replyCount = replyRepository.replyCount(board.getId());
            List<Album> albums = albumRepository.findByBoardId(board.getId());
            List<BoardResponse.SocialDetailDTO.AlbumDTO> albumDTOs = albums.stream()
                    .map(BoardResponse.SocialDetailDTO.AlbumDTO::new)
                    .toList();

            // 좋아요 여부 확인
            Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;

            // 북마크 여부 확인
            Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;

            Reply reply = replyRepository.findByBoardId(board.getId());

            List<Hashtag> hashtags = hashtagRepository.findByBoardId(board.getId());


            Boolean hashEmpty = false;

            // BoardDTO 객체 생성
            BoardResponse.SocialDetailDTO.BoardDTO boardDTO = new BoardResponse.SocialDetailDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked, hashtags, user.getImage(), hashEmpty, reply);

            if (boardDTO != null && boardDTO.getHashtagList() != null && !boardDTO.getHashtagList().isEmpty()) {
                if (boardDTO.getHashtagList() != null && !boardDTO.getHashtagList().isEmpty()) {
                    if (boardDTO.getHashtagList().get(0).getName().equals("")) {
                        hashEmpty = true;
                        boardDTO = new BoardResponse.SocialDetailDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked, hashtags, user.getImage(), hashEmpty, reply);
                    }
                }
            }
            boardDTOs.add(boardDTO);
        }

        if (isWaiting) {
            if (week.isEmpty() || week.get(0).length == 0) {
                return new BoardResponse.SocialDetailDTO(hashtag, social, socialMember.getUserId().getNickname(), socialMemberCount, boardDTOs, boards.size(), "월요일", finalResults, isWaiting);
            } else {
                return new BoardResponse.SocialDetailDTO(hashtag, social, socialMember.getUserId().getNickname(), socialMemberCount, boardDTOs, boards.size(), dayNameMap.get(week.get(0)[0]), finalResults, isWaiting);
            }
        } else {
            if (week.isEmpty() || week.get(0).length == 0) {
                return new BoardResponse.SocialDetailDTO(hashtag, social, socialMember.getUserId().getNickname(), socialMemberCount, boardDTOs, boards.size(), "월요일", finalResults);
            } else {
                return new BoardResponse.SocialDetailDTO(hashtag, social, socialMember.getUserId().getNickname(), socialMemberCount, boardDTOs, boards.size(), dayNameMap.get(week.get(0)[0]), finalResults);
            }
        }
    }

    public Boolean notJoinedSocial(Integer socialId, Integer userId) {

        return socialMemberRepository.isApproved(socialId, userId);
    }


    // 새로운 소셜 생성
    @Transactional
    public void createSocial(SocialRequest.Create createDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        // 이미 존재하는 소셜명인지 확인
        Optional<Social> socialNameCheck = socialRepository.findByName(createDTO.getName());
        if (socialNameCheck.isPresent()) {
            throw new Exception400("해당 소셜명은 이미 존재하는 소셜명입니다.");
        }

        ImageVideoUtil.FileUploadResult image = ImageVideoUtil.uploadFile(createDTO.getImage());

        // 새로운 소셜 생성
        Social social = Social.builder()
                .name(createDTO.getName())
                .image(image.getFilePath())
                .info(createDTO.getInfo())
                .status(SocialStateEnum.ACTIVE)
                .build();


        List<CategoryName> categoryNames = createDTO.getCategories().stream().map(s -> {
            return CategoryName.builder()
                    .name(s)
                    .status(DeleteStateEnum.ACTIVE)
                    .build();
        }).toList();

        for (int i = 0; i < categoryNames.size(); i++) {
            CategoryName categoryName = categoryNames.get(i);
            CategoryName categoryName2 = categoryNameRepository.save(CategoryName.builder()
                    .name(categoryName.getName())
                    .imagePath("이미지")
                    .status(DeleteStateEnum.ACTIVE)
                    .build());

            categoryRepository.save(Category.builder()
                    .socialId(social)
                    .categoryNameId(categoryName2)
                    .build());
        }

        Social saveSocial = socialRepository.save(social);

        // 소셜 멤버 등록 및 권한 부여
        SocialMember socialMember = SocialMember.builder()
                .socialId(saveSocial)
                .userId(user)
                .role(SocialMemberRoleEnum.MANAGER)
                .state(SocialMemberStateEnum.APPROVED)
                .build();

        socialMemberRepository.save(socialMember);
    }

    // 소셜 정보 수정
    @Transactional
    public void updateSocial(Integer socialId, SocialRequest.Update updateDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("해당 소셜은 존재하지 않습니다."));

        // 소셜명 중복 체크 (자기 자신 제외)
        Optional<Social> SocialNameCheck = socialRepository.findByName(updateDTO.getName());
        if (SocialNameCheck.isPresent() && !SocialNameCheck.get().getId().equals(social.getId())) {
            throw new Exception400("해당 소셜명은 이미 존재하는 소셜명입니다.");
        }


        ImageVideoUtil.FileUploadResult image = ImageVideoUtil.uploadFile(updateDTO.getImage());

        // 소셜 업데이트
        social.setName(updateDTO.getName());
        social.setImage(image.getFilePath());
        social.setInfo(updateDTO.getInfo());

        for (int i = 0; i < updateDTO.getCategories().size(); i++) {
            CategoryName categoryName = categoryNameRepository.findNameByCategoryName(updateDTO.getCategories().get(i));
            if (categoryName != null) {
                Category category = Category.builder()
                        .categoryNameId(categoryName)
                        .build();
                category.setCategoryNameId(categoryName);
                categoryName.setName(updateDTO.getCategories().get(i));
            } else {
                CategoryName categoryName1 = CategoryName.builder()
                        .imagePath("이미지")
                        .name(updateDTO.getCategories().get(i))
                        .status(DeleteStateEnum.ACTIVE)
                        .build();
                categoryNameRepository.save(categoryName1);

                categoryRepository.save(Category.builder()
                        .socialId(social)
                        .categoryNameId(categoryName1)
                        .build());
            }
        }
    }

    // 소셜 삭제
    @Transactional
    public void deleteSocial(Integer id) {
        Social social = socialRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 소셜은 존재하지 않습니다."));

        // 상태를 DELETED로 변경
        social.setStatus(SocialStateEnum.DELETED);
        socialRepository.save(social);
    }

    // 소셜 리스트 조회 (관리자)
    public SocialResponse.SocialListDTO getSocialList() {
        Integer count = socialRepository.findAllActiveSocial();
        List<Social> socialListDTO = socialRepository.findAllActiveSocialList();

        List<SocialResponse.SocialListDTO.SocialList> socialList = socialListDTO.stream()
                .map(social -> new SocialResponse.SocialListDTO.SocialList(
                        social.getId(),
                        social.getName(),
                        social.getCategory().stream().map(category -> category.getCategoryNameId().getName()).collect(Collectors.toList()),
                        socialMemberRepository.countBySocialId(social.getId()),
                        LocalDateTimeFormatter.convert(social.getCreatedAt())
                ))
                .collect(Collectors.toList());

        return new SocialResponse.SocialListDTO(count, socialList);
    }

    // 소셜 상세 조회 (관리자)
    public SocialResponse.DetailDTO getSocialDetail(Integer socialId) {
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("해당 소셜은 존재하지 않습니다."));
        List<SocialMember> socialMemberListDTO = socialMemberRepository.findSocialMembersBySocialId(social.getId());

        SocialResponse.DetailDTO.Detail detail = new SocialResponse.DetailDTO.Detail(social);
        Integer memberCount = socialMemberRepository.findAllBySocialMemberState(social.getId());
        List<SocialResponse.DetailDTO.SocialMemberList> socialMemberList = socialMemberListDTO.stream()
                .map(SocialResponse.DetailDTO.SocialMemberList::new)
                .collect(Collectors.toList());

        return new SocialResponse.DetailDTO(detail, memberCount, socialMemberList);
    }

    // 소셜 별 앨범, 파일 리스트 출력
    public SocialResponse.AlbumAndFileListDTO getSocialAlbumList(Integer socialId) {
        // 소셜 별 앨범 리스트 가져오기
        List<Album> albumList = albumRepository.findBySocialId(socialId);
        List<File> fileList = fileRepository.findBySocialId(socialId);


        // 소셜 조회
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("해당 소셜은 존재하지 않습니다."));
        // 소셜 멤버 수
        Integer socialMemberCount = socialMemberRepository.countBySocialId(socialId);
        // 소셜 리더
        SocialMember socialMemberLeader = socialMemberRepository.findBySocialId(socialId);

        // 소셜에 앨범이 비었을 때 null 을 반환
        if (albumList == null) {
            albumList = Collections.emptyList();
        }

        // 소셜에 파일이 비었을 때 null 을 반환
        if (fileList == null) {
            fileList = Collections.emptyList();
        }

        // 앨범, 파일 리스트 DTO 담기
        return new SocialResponse.AlbumAndFileListDTO(social, albumList, fileList, socialMemberCount, socialMemberLeader.getUserId().getNickname());
    }

    public List<UserResponse.MainDTO.MySocialDTO> getMySocialList(Integer userId) {
        List<Object[]> mySocialList = userQueryRepository.mySocialList(userId);
        return mySocialList.stream().map(UserResponse.MainDTO.MySocialDTO::new).toList();
    }

    public SocialResponse.MyApplySocialListDTO myApplySocial(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        List<SocialMember> socialList = socialMemberRepository.findByMyApply(user.getId());

        List<Integer> members = new ArrayList<>();

        for (int i = 0; i < socialList.size(); i++) {
            Integer socialMemberCount = socialMemberRepository.findAllBySocialMemberState(socialList.get(i).getSocialId().getId());
            members.add(socialMemberCount);
        }

        return new SocialResponse.MyApplySocialListDTO(socialList, members);
    }

    public SocialResponse.UpdateFormDTO updateForm(Integer socialId, Integer userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        // 소셜 리더
        SocialMember socialMember = socialMemberRepository.findByManager(socialId, user.getId());

        if (socialMember == null) {
            throw new Exception403("소셜 매니저만 소셜을 수정할 수 있습니다.");
        }

        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("소셜을 찾을 수 없습니다."));

        List<Category> category = categoryRepository.findBySocialId(socialId);

        return new SocialResponse.UpdateFormDTO(social, category);
    }
}

package com.example.project.social;

import com.example.project._core.utils.UserUtil;
import com.example.project.board.BoardResponse;
import com.example.project.board.BoardService;
import com.example.project.file.FileRequest;
import com.example.project.file.FileService;
import com.example.project.social_member.SocialMember;
import com.example.project.social_member.SocialMemberResponse;
import com.example.project.social_member.SocialMemberService;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SocialController {
    private final SocialService socialService;
    private final FileService fileService;
    private final HttpSession session;
    private final UserUtil userUtil;
    private final SocialMemberService socialMemberService;


    // 소셜 상세보기
    @GetMapping("/social/detail/{socialId}")
    public String socialDetail(@PathVariable int socialId, HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        BoardResponse.SocialDetailDTO modal = socialService.socialDetail(socialId, sessionUser.getId());

        request.setAttribute("modal", modal);

        Boolean notJoinedSocial = socialService.notJoinedSocial(socialId, sessionUser.getId());

        if (!notJoinedSocial) {
            return "social/notJoinedForm";
        }

        return "social/detail";
    }

    //소셜 디테일 -> 멤버 탭
    @GetMapping("/social/detail/{socialId}/member")
    public String socialMember(@PathVariable int socialId, HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        BoardResponse.SocialDetailDTO modal = socialService.socialDetail(socialId, sessionUser.getId());
        request.setAttribute("modal", modal);

        //소셜에 가입한 멤버 정보 가져오기
        List<SocialMemberResponse.SocialMemberDTO> socialMemberList = socialMemberService.getSocialMembersBySocialId(socialId);
//        System.out.println("socialMemberList = " + socialMemberList);
        request.setAttribute("socialMemberList", socialMemberList);

        //소셜에 등록한 글&댓글 수 가져오기
        SocialMemberResponse.ArticleCount counts = socialMemberService.getArticleCount(sessionUser.getId(), socialId);
        request.setAttribute("counts", counts);

        return "member/memberInvite";
    }

//    // 가입하지 않은 소셜 둘러보기 페이지
//    @GetMapping("/social/notJoined")
//    public String socialNotJoin() {
//        return "social/notJoinedForm";
//    }

    // 새 소셜 추가하기 페이지
    @GetMapping("/social/socialAdd")
    public String socialAdd() {
        return "social/socialaddForm";
    }

    // 서랍 페이지
    @GetMapping("/social/fileadd/{socialId}")
    public String fileAdd(@PathVariable Integer socialId, HttpServletRequest request) {
        // 페이지에 뿌릴 데이터
        SocialResponse.AlbumAndFileListDTO respDTO = socialService.getSocialAlbumList(socialId);
        request.setAttribute("models", respDTO);

        return "social/fileaddForm";
    }

    //내 소셜 목록을 가져오기. ajax로 가져오기 위해 json으로 리턴한다.
    @GetMapping("/social/get/my")
    public @ResponseBody List<UserResponse.MainDTO.MySocialDTO> getMySocialList() {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        List<UserResponse.MainDTO.MySocialDTO> socialList = socialService.getMySocialList(sessionUser.getId());
        return socialList;
    }


    // 파일 저장
    @PostMapping("/social/file/upload/{socialId}")
    public String fileUpdate(@PathVariable Integer socialId, FileRequest.FileUploadDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();

        fileService.fileUpload(reqDTO, sessionUser.getId(), socialId);
        return "redirect:/social/fileadd/" + reqDTO.getSocialId();
    }
}
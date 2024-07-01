package com.example.project.social;

import com.example.project._core.utils.ApiUtil;
import com.example.project._core.utils.UserUtil;
import com.example.project.board.BoardResponse;
import com.example.project.social_member.SocialMemberResponse;
import com.example.project.social_member.SocialMemberService;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SocialController {
    private final SocialService socialService;
    private final UserUtil userUtil;
    private final SocialMemberService socialMemberService;

    // 소셜 생성
    @PostMapping("/social/create")
    public String create(SocialRequest.Create CreateDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        socialService.createSocial(CreateDTO, sessionUser.getId());
        return "redirect:/";
    }

    // 소셜 수정
    @PostMapping("/social/update/{socialId}")
    public String update(@PathVariable Integer socialId, SocialRequest.Update UpdateDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        socialService.updateSocial(socialId, UpdateDTO, sessionUser.getId());
        return "redirect:/social/detail/" + socialId;
    }

    // 소셜 수정 폼
    @GetMapping("/social/updateForm/{socialId}")
    public String socialUpdateForm(@PathVariable("socialId") Integer socialId, HttpServletRequest request) throws IOException {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        SocialResponse.UpdateFormDTO model = socialService.updateForm(socialId, sessionUser.getId());
        request.setAttribute("model", model);

        return "social/socialUpdateForm";
    }

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
    public String socialMember(@PathVariable int socialId, HttpServletRequest request, @RequestParam(defaultValue = "userName") String sortBy) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        SocialMemberResponse.MemberDTO modal = socialMemberService.member(sessionUser.getId(), socialId);
        request.setAttribute("modal", modal);

        //소셜에 가입한 멤버 정보 가져오기
        List<SocialMemberResponse.SocialMemberDTO> socialMemberList = socialMemberService.getSocialMembersBySocialId(socialId, sortBy);
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
    public String socialAdd(HttpServletRequest request) {
        SocialResponse.SaveFormDTO model = socialService.saveForm();
        request.setAttribute("model", model);

        return "social/socialaddForm";
    }

    // 앨범 및 파일 페이지
    @GetMapping("/social/fileadd/{socialId}")
    public String fileAdd(@PathVariable Integer socialId, HttpServletRequest request) {
        SocialResponse.AlbumAndFileListDTO respDTO = socialService.getSocialAlbumList(socialId);
        request.setAttribute("modal", respDTO);
        return "social/fileaddForm";
    }

    //내 소셜 목록을 가져오기. ajax로 가져오기 위해 json으로 리턴한다.
    @GetMapping("/social/get/my")
    public @ResponseBody List<UserResponse.MainDTO.MySocialDTO> getMySocialList() {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        List<UserResponse.MainDTO.MySocialDTO> socialList = socialService.getMySocialList(sessionUser.getId());
        return socialList;
    }


//    // 파일 저장 ajax 로 인해 json 리턴
//    @PostMapping("/social/file/upload/{socialId}")
//    @ResponseBody
//    public ResponseEntity<?> fileUpdate(@PathVariable Integer socialId, FileRequest.FileUploadDTO reqDTO) {
//        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
//        fileService.fileUpload(reqDTO, sessionUser.getId(), socialId);
//        return ResponseEntity.ok().body(Map.of("message", "File uploaded successfully"));
//    }
}
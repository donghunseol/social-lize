package com.example.project.mypage;

import com.example.project._core.utils.UserUtil;
import com.example.project.board.BoardResponse;
import com.example.project.board.BoardService;
import com.example.project.bookmark.BookmarkService;
import com.example.project.social.SocialService;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MypageController {
    private final SocialService socialService;
    private final BoardService boardService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 북마크 페이지
    @GetMapping("/bookmark/my/list")
    public String bookmark(HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        BoardResponse.SocialDetailDTO modal = boardService.boardList(sessionUser.getId());
        request.setAttribute("modals", modal);

        return "/mypage/bookmarkForm";
    }

    // 가입신청 현황 페이지
    @GetMapping("/mypage/joinstatus")
    public String joinstatus() {
        return "/mypage/joinstatusForm";
    }


    // 프로필 업데이트 폼
    @GetMapping("/mypage/profileUpdateForm")
    public String profileUpdateForm() {
        return "/mypage/profileUpdateForm";
    }

    // 내가 쓴 글 페이지
    @GetMapping("/mypage/myrecord")
    public String myrecord () {
        return "/mypage/myrecordForm";
    }
}

package com.example.project.mypage;

import com.example.project._core.utils.ApiUtil;
import com.example.project._core.utils.UserUtil;
import com.example.project.board.BoardResponse;
import com.example.project.board.BoardService;
import com.example.project.bookmark.BookmarkService;
import com.example.project.social.SocialResponse;
import com.example.project.social.SocialService;
import com.example.project.user.UserResponse;
import com.example.project.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MypageController {
    private final SocialService socialService;
    private final BoardService boardService;
    private final BookmarkService bookmarkService;
    private final UserService userService;
    private final UserUtil userUtil;

    // 북마크 페이지
    @GetMapping("/bookmark/my/list")
    public String bookmark(HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        BoardResponse.BookMarkBoardListDTO modal = boardService.boardList(sessionUser.getId());
        request.setAttribute("modals", modal);

        return "/mypage/bookmarkForm";
    }

    // 가입신청 현황 페이지
    @GetMapping("/mypage/joinstatus")
    public String joinstatus(HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        SocialResponse.MyApplySocialListDTO model = socialService.myApplySocial(sessionUser.getId());
        request.setAttribute("model", model);

        return "/mypage/joinstatusForm";
    }

    @GetMapping("/mypage/profileUpdateForm")
    public String profileUpdateForm() {


        return "/mypage/profileUpdateForm";
    }


    // 내가 쓴 글 페이지
    @GetMapping("/mypage/myrecord")
    public String myrecord (HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        BoardResponse.MyBoardAndReplyListDTO myBoardModel = boardService.myBoardAndReply(sessionUser.getId());
        request.setAttribute("myBoardModel", myBoardModel);

        return "/mypage/myrecordForm";
    }
}

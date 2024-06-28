package com.example.project.reply;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.User;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 댓글 삭제
    @PutMapping("/board/{id}/reply/delete")
    public String delete(@PathVariable Integer id) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        replyService.delete(id, sessionUser.getId());
        return "redirect:/social/detail/" + id;
    }

    // 댓글 쓰기
    @PostMapping("/reply/save")
    public String save(@RequestParam("socialId") Integer socialId,
                       @RequestParam("boardId") Integer boardId,
                       @RequestParam("role") Integer role,
                       ReplyRequest.SaveDTO reqDTO) {

        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        replyService.save(reqDTO, sessionUser.getId(), boardId);

        if (role == 1) {
            return "redirect:/social/detail/" + socialId;
        } else if (role == 2) {
            return "redirect:/bookmark/my/list";
        } else if (role == 3) {
            return "redirect:/mypage/myrecord";
        } else {
            return "redirect:/mypage/myrecord";
        }
    }
}

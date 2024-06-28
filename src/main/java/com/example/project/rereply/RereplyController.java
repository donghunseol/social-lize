package com.example.project.rereply;

import com.example.project._core.utils.UserUtil;
import com.example.project.reply.ReplyRequest;
import com.example.project.user.User;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class RereplyController {
    private final RereplyService rereplyService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 대댓글 삭제
    @PostMapping("/rereply/delete")
    public String delete(@PathVariable Integer boardId, @PathVariable Integer replyId, @PathVariable Integer rereplyId, @PathVariable Integer id){
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        rereplyService.delete(rereplyId, sessionUser.getId());
        return "redirect:/social/detail/" + boardId;
    }

    // 대댓글 쓰기
    @PostMapping("/rereply/save")
    public String save(@RequestParam("replyId") Integer replyId,
                       @RequestParam("boardId") Integer boardId,
                       RereplyRequest.SaveDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        rereplyService.save(reqDTO, sessionUser.getId(), boardId, replyId);
        return "redirect:/social/detail/" + boardId;
    }
}

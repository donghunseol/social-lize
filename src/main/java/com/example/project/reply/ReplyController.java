package com.example.project.reply;

import com.example.project.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    // 댓글 삭제
    @PostMapping("/board/{boardId}/reply/{replyId}/delete")
    public String delete(@PathVariable Integer boardId, @PathVariable Integer replyId){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.delete(replyId, sessionUser.getId());
        return "redirect:/social/detail/" + boardId;
    }
}

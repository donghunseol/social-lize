package com.example.project.rereply;

import com.example.project.reply.ReplyRequest;
import com.example.project.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RereplyController {
    private final RereplyService rereplyService;
    private final HttpSession session;

    // 대댓글 삭제
    @PostMapping("/board/{boardId}/rereply/{replyId}/delete")
    public String delete(@PathVariable Integer boardId, @PathVariable Integer replyId, @PathVariable Integer rereplyId){
        User sessionUser = (User) session.getAttribute("sessionUser");
        rereplyService.delete(rereplyId, sessionUser.getId());
        return "redirect:/social/detail/" + boardId;
    }

    // 대댓글 쓰기
    @PostMapping("/board/{boardId}/rereply/save")
    public String save(@PathVariable Integer boardId, @PathVariable Integer replyId, RereplyRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        reqDTO.setReplyId(replyId);
        rereplyService.save(reqDTO, sessionUser);
        return "redirect:/social/detail/" + boardId;
    }
}

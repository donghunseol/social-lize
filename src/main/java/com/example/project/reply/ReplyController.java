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
    @PostMapping("/board/{id}/reply/delete")
    public String delete(@PathVariable Integer id){
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        replyService.delete(id, sessionUser.getId());
        return "redirect:/social/detail/" + id;
    }

    // 댓글 쓰기
    @PostMapping("/board/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.save(reqDTO, sessionUser);
        return "redirect:/social/detail/" + reqDTO.getBoardId();
    }
}

package com.example.project.qna;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class QnaController {
    private final QnaService qnaService;
    private final HttpSession session;
    private final UserUtil userUtil;

    @GetMapping("/qna")
    public String detail() {
        return "layout/qna";
    }

    // 문의 수정
    @PutMapping("/qna/update/{qnaId}")
    public String update(@PathVariable("qnaId") Integer qnaId, QnaRequest.UpdateDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        qnaService.update(qnaId, sessionUser.getId(), reqDTO);

        return "redirect:/qna/my/list";
    }

    // 문의 삭제
    @PutMapping("/qna/delete/{qnaId}")
    public String delete(@PathVariable("qnaId") Integer qnaId) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        qnaService.delete(qnaId, sessionUser.getId());

        return "redirect:/qna/my/list";
    }
}

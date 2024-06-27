package com.example.project.qna;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class QnaController {
    private final QnaService qnaService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 문의 상세보기
    @GetMapping("/qna/detail/{qnaId}")
    public String detail(@PathVariable("qnaId") Integer qnaId, HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        QnaResponse.QnaDetailDTO modal = qnaService.detail(sessionUser.getId(), qnaId);
        request.setAttribute("modal", modal);

        return null;
    }

    // 내 문의 리스트
    @GetMapping("/qna/my/list")
    public String list(HttpServletRequest request) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        QnaResponse.QnaListDTO modal = qnaService.list(sessionUser.getId());

        request.setAttribute("modal", modal);

        return "qna/qnaList";
    }

    // 문의 작성
    @PostMapping("/qna")
    public String save(QnaRequest.SaveDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        qnaService.save(sessionUser.getId(), reqDTO);

        return "redirect:/qna/my/list";
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
        Integer userId = 1;
        qnaService.delete(qnaId, userId);

        return "redirect:/qna/my/list";
    }
}

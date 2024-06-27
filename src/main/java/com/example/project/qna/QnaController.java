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

    @GetMapping("/qna")
    public String detail() {
        return "layout/qna";
    }



//    @GetMapping("/qna/detail/{qnaId}")
//    public String detail(@PathVariable("qnaId") Integer qnaId, HttpServletRequest request) {
//        Integer userId = 1;
//        QnaResponse.QnaDetailDTO modal = qnaService.detail(userId, qnaId);
//        request.setAttribute("modal", modal);
//
//        return null;
//    }
//
//
//    @GetMapping("/qna/my/list")
//    public String list(HttpServletRequest request) {
//        Integer userId = 1;
//        QnaResponse.QnaListDTO modal = qnaService.list(userId);
//
//        request.setAttribute("modal", modal);
//
//        return "qna/qnaList";
//    }

    @PostMapping("/qna")
    public String save(QnaRequest.SaveDTO reqDTO) {
        Integer userId = 1;
        qnaService.save(userId, reqDTO);

        return "redirect:/qna/my/list";
    }

    @PutMapping("/qna/update/{qnaId}")
    public String update(@PathVariable("qnaId") Integer qnaId, QnaRequest.UpdateDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        qnaService.update(qnaId, sessionUser.getId(), reqDTO);

        return "redirect:/qna/my/list";
    }

    @PutMapping("/qna/delete/{qnaId}")
    public String delete(@PathVariable("qnaId") Integer qnaId) {
        Integer userId = 1;
        qnaService.delete(qnaId, userId);

        return "redirect:/qna/my/list";
    }
}

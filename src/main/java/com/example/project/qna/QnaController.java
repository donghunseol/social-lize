package com.example.project.qna;

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

    @GetMapping("/qna/detail/{qnaId}")
    public String detail(@PathVariable("qnaId") Integer qnaId, HttpServletRequest request) {
        Integer userId = 1;
        QnaResponse.QnaDetailDTO detail = qnaService.detail(userId, qnaId);
        request.setAttribute("detail", detail);

        return null;
    }


    @GetMapping("/qna/my/list")
    public String list(HttpServletRequest request) {
        Integer userId = 1;
        QnaResponse.QnaListDTO qnaList = qnaService.list(userId);

        request.setAttribute("qnaList", qnaList);

        return "qna/qnaList";
    }

    @PostMapping("/qna")
    public String save(QnaRequest.SaveDTO reqDTO) {
        Integer userId = 1;
        qnaService.save(userId, reqDTO);

        return "redirect:/qna/my/list";
    }

    @PutMapping("/qna/update/{qnaId}")
    public String update(@PathVariable("qnaId") Integer qnaId, QnaRequest.UpdateDTO reqDTO) {
        Integer userId = 1;
        qnaService.update(qnaId, userId, reqDTO);

        return "redirect:/qna/my/list";
    }

    @PutMapping("/qna/delete/{qnaId}")
    public String delete(@PathVariable("qnaId") Integer qnaId) {
        Integer userId = 1;
        qnaService.delete(qnaId, userId);

        return "redirect:/qna/my/list";
    }
}

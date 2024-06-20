package com.example.project.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/{socialId}")
    public String socialBoardSave(@PathVariable Integer socialId, BoardRequest.SaveDTO reqDTO) {
        Integer userId = 1;
        boardService.save(socialId, reqDTO, userId);

        return "redirect:/social/detail/" + socialId;
    }
}

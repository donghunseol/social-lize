package com.example.project.board;

import com.example.project.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/{socialId}")
    public String save(@PathVariable Integer socialId, BoardRequest.SaveDTO reqDTO) {
//        User user = (User) session.getAttribute("user");
        boardService.save(socialId, reqDTO, 1);

        // 나머지 데이터 처리 로직
        return "redirect:/social/detail/" + socialId;
    }
}

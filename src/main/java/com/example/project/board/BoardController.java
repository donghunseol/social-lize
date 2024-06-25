package com.example.project.board;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.User;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;
    private final UserUtil userUtil;

    @PostMapping("/board/{socialId}")
    public String save(@PathVariable Integer socialId, BoardRequest.SaveDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        boardService.save(socialId, reqDTO, sessionUser.getId());

        // 나머지 데이터 처리 로직
        return "redirect:/social/detail/" + socialId;
    }
}

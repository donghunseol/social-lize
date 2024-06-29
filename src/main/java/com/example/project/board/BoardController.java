package com.example.project.board;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.User;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;
    private final UserUtil userUtil;

    // 게시글 작성하기
    @PostMapping("/board/{socialId}")
    public String save(@PathVariable Integer socialId, BoardRequest.SaveDTO reqDTO) {
        System.out.println("DTO TEST : " + reqDTO);
        System.out.println("동영상 이름 : " + reqDTO.getVideoFiles().getFirst().getOriginalFilename());
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        boardService.save(socialId, reqDTO, sessionUser.getId());

        System.out.println("-------------------------------------------------------------" + reqDTO.getVideoFiles().getFirst().getOriginalFilename());
        // 나머지 데이터 처리 로직
        return "redirect:/social/detail/" + socialId;
    }

    // 게시글 삭제하기
    @PostMapping("/board/delete/{boardId}/{socialId}")
    public String delete(@PathVariable("boardId") Integer boardId,
                         @PathVariable("socialId") Integer socialId,
                         @RequestParam("role") Integer role) {

        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();

        boardService.delete(sessionUser.getId(), boardId);

        if (role == 2) {
            return "redirect:/mypage/myrecord";
        }

        return "redirect:/social/detail/" + socialId;
    }
}

package com.example.project.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/{socialId}")
    public String socialBoardSave(@PathVariable Integer socialId, @ModelAttribute BoardRequest.SaveDTO reqDTO) {
        Integer userId = 1;

        System.out.println(reqDTO.getContent());
        System.out.println(reqDTO.getImgFiles().size()); // 이미지 파일 개수 확인
        System.out.println(reqDTO.getVideoFiles().size()); // 동영상 파일 개수 확인
        boardService.save(socialId, reqDTO, userId);

        return "redirect:/social/detail/" + socialId;
    }
}

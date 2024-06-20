package com.example.project.board;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/{socialId}")
    public String save(
            @PathVariable Integer socialId,
            @RequestParam("imgFiles") List<MultipartFile> imgFiles,
            @RequestParam("videoFiles") List<MultipartFile> videoFiles,
            BoardRequest.SaveDTO reqDTO) {

        // reqDTO를 이용한 필요한 데이터 처리
        System.out.println("글 내용: " + reqDTO.getContent());

        // 이미지 파일 처리
        for (int i = 0; i < imgFiles.size() - 1; i++) {
            MultipartFile imgFile = imgFiles.get(i);
            if (!imgFile.isEmpty()) {
                System.out.println("이미지 파일명: " + imgFile.getOriginalFilename());
            }
        }

        // 동영상 파일 처리
        for (int i = 0; i < videoFiles.size() - 1; i++) {
            MultipartFile videoFile = videoFiles.get(i);
            if (!videoFile.isEmpty()) {
                System.out.println("동영상 파일명: " + videoFile.getOriginalFilename());
                // 여기서 동영상 파일을 저장하거나 다른 작업 수행 가능
            }
        }

        // 나머지 데이터 처리 로직
        return "redirect:/social/detail/" + socialId;
    }
}

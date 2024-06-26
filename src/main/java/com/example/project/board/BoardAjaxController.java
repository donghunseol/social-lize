package com.example.project.board;

import com.example.project._core.utils.HlsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BoardAjaxController {
    private final BoardService boardService;

    // 게시글 상세보기
    @GetMapping("/board/detail/")
    public ResponseEntity<?> detail(@RequestParam Integer boardId) {
        BoardResponse.BoardDetailDTO detail = boardService.detail(boardId);

        return ResponseEntity.ok(detail);
    }
}

package com.example.project.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardAjaxController {
    private final BoardService boardService;

    @GetMapping("/board/detail/")
    public ResponseEntity<?> detail(@RequestParam Integer boardId) {
        BoardResponse.BoardDetailDTO detail = boardService.detail(boardId);

        return ResponseEntity.ok(detail);
    }
}

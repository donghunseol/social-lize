package com.example.project.bookmark;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final HttpSession session;

    @PostMapping("/bookmark/save")
    public ResponseEntity<?> save(@RequestParam("boardId") Integer boardId) {
        Integer userId = 1;
        Boolean success = bookmarkService.save(boardId, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/bookmark/delete")
    public ResponseEntity<?> delete(@RequestParam("boardId") Integer boardId) {
        Integer userId = 1;
        Boolean success = bookmarkService.delete(boardId, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
}

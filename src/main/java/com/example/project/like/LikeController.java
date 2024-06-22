package com.example.project.like;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class LikeController {
    private final LikeService likeService;
    private final HttpSession session;

    @PostMapping("/like/save")
    public ResponseEntity<?> save(@RequestParam("boardId") Integer boardId) {
        Integer userId = 1;
        Boolean success = likeService.save(boardId, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/like/delete")
    public ResponseEntity<?> delete(@RequestParam("boardId") Integer boardId) {
        Integer userId = 1;
        Boolean success = likeService.delete(boardId, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
}

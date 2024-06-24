package com.example.project.like;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
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
    private final UserUtil userUtil;

    @PostMapping("/like/save")
    public ResponseEntity<?> save(@RequestParam("boardId") Integer boardId) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        Boolean success = likeService.save(boardId, sessionUser.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/like/delete")
    public ResponseEntity<?> delete(@RequestParam("boardId") Integer boardId) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        Boolean success = likeService.delete(boardId, sessionUser.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
}

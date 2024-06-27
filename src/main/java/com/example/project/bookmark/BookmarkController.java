package com.example.project.bookmark;

import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
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
    private final UserUtil userUtil;

    // 북마크 하기
    @PostMapping("/bookmark/save")
    public ResponseEntity<?> save(@RequestParam("boardId") Integer boardId) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        Boolean success = bookmarkService.save(boardId, sessionUser.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }

    // 북마크 취소
    @DeleteMapping("/bookmark/delete")
    public ResponseEntity<?> delete(@RequestParam("boardId") Integer boardId) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        Boolean success = bookmarkService.delete(boardId, sessionUser.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
}

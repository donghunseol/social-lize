package com.example.project.user;

import com.example.project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;
    private final HttpSession session;

    // 회원 리스트 조회 (관리자)
    @GetMapping("/admin/user-list")
    public ResponseEntity<?> userList() {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<UserResponse.UserList> userList = userService.getUserList();
        return ResponseEntity.ok(new ApiUtil<>(userList));
    }
}

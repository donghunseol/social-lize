package com.example.project.user;

import com.example.project._core.utils.ApiUtil;
import com.example.project.category_name.CategoryNameRequest;
import com.example.project.category_name.CategoryNameResponse;
import com.example.project.category_name.CategoryNameService;
import com.example.project.social.SocialResponse;
import com.example.project.social.SocialService;
import com.example.project.social_member.SocialMemberResponse;
import com.example.project.social_member.SocialMemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
    private final SocialService socialService;
    private final SocialMemberService socialMemberService;
    private final CategoryNameService categoryNameService;
    private final UserService userService;

    // 회원 리스트 조회
    @GetMapping({"/", "/user-list"})
    public String mainPage(HttpServletRequest request) {
        List<UserResponse.UserList> userList = userService.getUserList();
        request.setAttribute("userList", userList);
        return "admin/user/userListForm";
    }

    // 회원 상세 조회
    @GetMapping("/user/{userId}/detail")
    public String userDetailPage(HttpServletRequest request, @PathVariable Integer userId) {
        UserResponse.UserDetail userDetail = userService.getUserDetail(userId);
        request.setAttribute("userDetail", userDetail);
        return "admin/user/userDetailForm";
    }
}

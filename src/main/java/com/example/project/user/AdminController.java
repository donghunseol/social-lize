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
    public String userListPage(HttpServletRequest request) {
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

    // 카테고리 리스트 조회
    @GetMapping("/category-name-list")
    public String categoryNameListPage(HttpServletRequest request) {
        List<CategoryNameResponse.CategoryDTO> categoryList = categoryNameService.getCategoryList();
        request.setAttribute("categoryList", categoryList);
        return "admin/social/socialCategoryListForm";
    }

    // 카테고리 상세 조회
    @GetMapping("/category-name/{categoryNameId}")
    public String categoryNameDetailPage(HttpServletRequest request, @PathVariable Integer categoryNameId) {
        CategoryNameResponse.Detail categoryDetail = categoryNameService.getCategoryDetail(categoryNameId);
        request.setAttribute("categoryDetail", categoryDetail);
        return "admin/social/socialCategoryDetailForm";
    }

    // 소셜 리스트 조회
    @GetMapping("/social-list")
    public String socialListPage(HttpServletRequest request) {
        List<SocialResponse.SocialDTO> socialList = socialService.getSocialList();
        request.setAttribute("socialList", socialList);
        return "admin/social/socialListForm";
    }

    // 소셜 상세 조회
    @GetMapping("/social/{socialId}")
    public String socialDetailPage(HttpServletRequest request, @PathVariable Integer socialId) {
        SocialResponse.Detail socialDetail = socialService.getSocialDetail(socialId);
        request.setAttribute("socialDetail", socialDetail);
        return "admin/social/socialDetailForm";
    }

    // 게시글 리스트 조회
    @GetMapping("/board-list")
    public String boardListPage(HttpServletRequest request) {
        return "admin/board/boardListForm";
    }

    // 게시글 상세 조회
    @GetMapping("/board/{boardId}")
    public String boardDetailPage(HttpServletRequest request, @PathVariable Integer boardId) {
        return "admin/board/boardDetailForm";
    }
}

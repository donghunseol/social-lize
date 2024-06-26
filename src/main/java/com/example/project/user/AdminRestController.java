package com.example.project.user;

import com.example.project._core.utils.ApiUtil;
import com.example.project.category_name.CategoryNameRequest;
import com.example.project.category_name.CategoryNameResponse;
import com.example.project.category_name.CategoryNameService;
import com.example.project.social.SocialResponse;
import com.example.project.social.SocialService;
import com.example.project.social_member.SocialMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminRestController {
    private final SocialService socialService;
    private final SocialMemberService socialMemberService;
    private final CategoryNameService categoryNameService;
    private final UserService userService;

    // 회원 리스트 조회
    @GetMapping("/user-list")
    public ResponseEntity<?> userList() {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.UserListDTO userListDTO = userService.getUserList();
        return ResponseEntity.ok(new ApiUtil<>(userListDTO));
    }

    // 회원 상세 조회
    @GetMapping("/detail/{userId}")
    public ResponseEntity<?> userDetail(@PathVariable Integer userId) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.UserDetail userDetail = userService.getUserDetail(userId);
        return ResponseEntity.ok(new ApiUtil<>(userDetail));
    }

    // 카테고리 등록
    @PostMapping("/category/create")
    public ResponseEntity<?> create(@RequestBody CategoryNameRequest.Create createDTO) {
        categoryNameService.createCategory(createDTO);
        return ResponseEntity.ok(new ApiUtil<>(createDTO));
    }

    // 카테고리 수정
    @PutMapping("/category/{categoryNameId}/update")
    public ResponseEntity<?> update(@PathVariable Integer categoryNameId, @RequestBody CategoryNameRequest.Update updateDTO) {
        categoryNameService.updateCategory(categoryNameId, updateDTO);
        return ResponseEntity.ok(new ApiUtil<>(updateDTO));
    }

    // 카테고리 삭제
    @PutMapping("/category/{categoryNameId}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer categoryNameId) {
        categoryNameService.deleteCategory(categoryNameId);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 카테고리 상세 조회
    @GetMapping("/category/{categoryNameId}")
    public ResponseEntity<?> categoryDetail(@PathVariable Integer categoryNameId) {
        CategoryNameResponse.Detail categoryDetail = categoryNameService.getCategoryDetail(categoryNameId);
        return ResponseEntity.ok(new ApiUtil<>(categoryDetail));
    }

    // 카테고리 리스트 조회
    @GetMapping("/category-list")
    public ResponseEntity<?> categoryList() {
        CategoryNameResponse.CategoryListDTO categoryListDTO = categoryNameService.getCategoryList();
        return ResponseEntity.ok(new ApiUtil<>(categoryListDTO));
    }

    // 소셜 리스트 조회
    @GetMapping("/social-list")
    public ResponseEntity<?> socialList() {
        SocialResponse.SocialListDTO socialListDTO = socialService.getSocialList();
        return ResponseEntity.ok(new ApiUtil<>(socialListDTO));
    }

    // 소셜 상세 조회
    @GetMapping("/social/{socialId}")
    public ResponseEntity<?> socialDetail(@PathVariable Integer socialId) {
        SocialResponse.DetailDTO socialDetail = socialService.getSocialDetail(socialId);
        return ResponseEntity.ok(new ApiUtil<>(socialDetail));
    }

    // 소셜 삭제
    @PutMapping("/social/{socialId}/delete")
    public ResponseEntity<?> socialDelete(@PathVariable Integer socialId) {
        socialService.deleteSocial(socialId);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

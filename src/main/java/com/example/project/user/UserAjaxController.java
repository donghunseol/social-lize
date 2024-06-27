package com.example.project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAjaxController {
    private final UserService userService;

    // 소셜 카테고리 리스트
    @GetMapping("/main/category")
    public ResponseEntity<?> getSocialByCategory(@RequestParam Integer categoryId) {
        UserResponse.MainAjaxDTO model = userService.mainAjax(categoryId);

        // categorySocialList만 반환
        return ResponseEntity.ok(model.getCategorySocialList());
    }
}

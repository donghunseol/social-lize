package com.example.project.user;

import com.example.project._core.utils.ApiUtil;
import com.example.project.category_name.CategoryNameRequest;
import com.example.project.category_name.CategoryNameResponse;
import com.example.project.category_name.CategoryNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminRestController {
    private final CategoryNameService categoryNameService;

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
        List<CategoryNameResponse.CategoryDTO> categoryList = categoryNameService.getCategoryList();
        return ResponseEntity.ok(new ApiUtil<>(categoryList));
    }
}

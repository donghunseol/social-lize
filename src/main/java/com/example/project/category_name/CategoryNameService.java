package com.example.project.category_name;

import com.example.project._core.enums.CategoryNameStateEnum;
import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.errors.exception.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryNameService {
    private final CategoryNameRepository categoryNameRepository;

    // 카테고리 저장
    @Transactional
    public void createCategory(CategoryNameRequest.Create createDTO) {
        // 이미 존재하는 카테고리명인지 확인
        Optional<CategoryName> categoryNameCheck = categoryNameRepository.findByName(createDTO.getName());
        if (categoryNameCheck.isPresent()) {
            throw new Exception400("이미 존재하는 카테고리명입니다.");
        }
        CategoryName categoryName = CategoryName.builder()
                .name(createDTO.getName())
                .imagePath(createDTO.getImagePath())
                .status(CategoryNameStateEnum.ACTIVE)
                .build();
        categoryNameRepository.save(categoryName);
    }

    // 카테고리 수정
    @Transactional
    public void updateCategory(Integer categoryNameId, CategoryNameRequest.Update updateDTO) {
        CategoryName categoryName = categoryNameRepository.findById(categoryNameId)
                .orElseThrow(() -> new Exception404("해당 카테고리는 존재하지 않습니다."));

        // 이미 존재하는 카테고리명인지 확인 (자기 자신 제외)
        Optional<CategoryName> categoryNameCheck = categoryNameRepository.findByName(updateDTO.getName());
        if (categoryNameCheck.isPresent() && !categoryNameCheck.get().getId().equals(categoryName.getId())) {
            throw new Exception400("이미 존재하는 카테고리명입니다.");
        }

        // 카테고리 업데이트
        categoryName.setName(updateDTO.getName());
        categoryName.setImagePath(updateDTO.getImagePath());

        categoryNameRepository.save(categoryName);
    }

    // 카테고리 삭제
    @Transactional
    public void deleteCategory(Integer categoryNameId) {
        CategoryName categoryName = categoryNameRepository.findById(categoryNameId)
                .orElseThrow(() -> new Exception404("해당 카테고리는 존재하지 않습니다."));

        categoryName.setStatus(CategoryNameStateEnum.DELETED);

        categoryNameRepository.save(categoryName);
    }

    // 카테고리 상세 조회
    public CategoryNameResponse.Detail getCategoryDetail(Integer categoryNameId) {
        CategoryName categoryName = categoryNameRepository.findById(categoryNameId)
                .orElseThrow(() -> new Exception404("해당 카테고리는 존재하지 않습니다."));
        if (categoryName.getStatus() == CategoryNameStateEnum.DELETED) {
            throw new Exception404("해당 카테고리는 삭제되었거나 존재하지 않습니다.");
        }

        return new CategoryNameResponse.Detail(categoryName);
    }

    // 카테고리 리스트 조회
    public List<CategoryNameResponse.CategoryDTO> getCategoryList() {
        List<CategoryName> categoryNameList = categoryNameRepository.findAllByStatus(CategoryNameStateEnum.ACTIVE);

        return categoryNameList.stream().map(CategoryNameResponse.CategoryDTO::new).toList();
    }
}

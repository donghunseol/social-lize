package com.example.project.user;

import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CategoryNameRepository categoryNameRepository;
    private final UserQueryRepository userQueryRepository;

    public UserResponse.MainDTO mainPage(Integer userId, Integer categoryId) {
        List<Object[]> mySocialList = userQueryRepository.mySocialList(userId);

        List<Object[]> mySocialPopularList = userQueryRepository.mySocialPopularList(userId);

        List<Object[]> popularPostList = userQueryRepository.popularPostList();

        List<CategoryName> categoryNameList = categoryNameRepository.findAll();

        List<Object[]> categorySocialList = userQueryRepository.categorySocialList(categoryId);

        return new UserResponse.MainDTO(mySocialList, mySocialPopularList, popularPostList, categoryNameList, categorySocialList);
    }
}

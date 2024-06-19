package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CategoryNameRepository categoryNameRepository;
    private final UserQueryRepository userQueryRepository;

    public UserResponse.MainDTO mainPage(Integer userId) {
        List<Object[]> mySocialList = userQueryRepository.mySocialList(userId);



        List<Object[]> mySocialPopularList = userQueryRepository.mySocialPopularList(userId);

        List<Object[]> popularPostList = userQueryRepository.popularPostList();

        List<CategoryName> categoryNameList = categoryNameRepository.findAll();


        return new UserResponse.MainDTO(mySocialList, mySocialPopularList, popularPostList, categoryNameList);
    }
    public void join(UserRequest.JoinDTO joinDTO) {
        User user = new User();
        user.setEmail(joinDTO.getEmail());
        user.setPassword(joinDTO.getPassword());
        user.setNickname(joinDTO.getName());

        user.setRole(
                UserEnum.fromString(joinDTO.getRole())
        );

        LocalDate bod = LocalDate.of(
                Integer.parseInt(joinDTO.getYear()),
                Integer.parseInt(joinDTO.getMonth()),
                Integer.parseInt(joinDTO.getDay())
        );
        user.setBirth(bod);
        System.out.println(user);
        userRepository.save(user);
    }
}

package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.utils.EncryptUtil;
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

    public UserResponse.MainDTO mainPage(Integer userId, Integer categoryId) {
        List<Object[]> mySocialList = userQueryRepository.mySocialList(userId);

        List<Object[]> mySocialPopularList = userQueryRepository.mySocialPopularList(userId);

        List<Object[]> popularPostList = userQueryRepository.popularPostList();

        List<CategoryName> categoryNameList = categoryNameRepository.findAll();

        List<Object[]> categorySocialList = userQueryRepository.categorySocialList(categoryId);

        return new UserResponse.MainDTO(mySocialList, mySocialPopularList, popularPostList, categoryNameList, categorySocialList);
    }
    //회원가입
    public void join(UserRequest.JoinDTO joinDTO) {
        User user = new User();
        user.setEmail(joinDTO.getEmail());

        user.setPassword(
                EncryptUtil.hashPw(joinDTO.getPassword())
        );
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
        userRepository.save(user);
    }
    //로그인
    public UserResponse.LoggedInUserDTO login(UserRequest.LoginDTO loginDTO) {
        String msg = "이메일 혹은 비밀번호가 일치하지 않습니다.";
        User user = userRepository.findByEmail(loginDTO.getEmail());

        //해당 이메일로 사용자가 검색되지 않을 경우(없는 회원)
        if(user == null) throw new RuntimeException(msg);

        //사용자가 입력한 비밀번호와 db에 저장된 비밀번호를 비교한다.
        //일치하는 경우
        if( EncryptUtil.checkPw(loginDTO.getPassword(), user.getPassword()) ){
            return new UserResponse.LoggedInUserDTO(user);
        }
        //비밀번호 불일치
        else throw new RuntimeException(msg);
    }
}

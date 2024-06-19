package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public void join(UserRequest.JoinDTO joinDTO) {
        User user = new User();
        user.setEmail(joinDTO.getEmail());
        user.setPassword(joinDTO.getPassword());
        user.setNickname(joinDTO.getName());
        user.setRole(UserEnum.USER);
//        user.setBirth();
        LocalDate bod = LocalDate.of(
                Integer.parseInt(joinDTO.getYear()),
                Integer.parseInt(joinDTO.getMonth()),
                Integer.parseInt(joinDTO.getDay())
        );
        System.out.println(user);
        //userRepository.save(user);
    }
}

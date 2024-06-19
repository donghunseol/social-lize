package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project._core.utils.EncryptUtil;
import com.example.project._core.utils.RuntimeConfiguration;
import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
    @Transactional
    public Object findByKakaoId(String code){

        // 1.1 RestTemplate 설정
        RestTemplate rt = new RestTemplate();

        // 1.2 http header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 1.3 http body 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", RuntimeConfiguration.KAKAO_APP_KEY);
        body.add("redirect_uri", RuntimeConfiguration.KAKAO_REDIRECT_URL);
        body.add("code", code);

        // 1.4 body+header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        // 1.5 api 요청하기 (토큰 받기)
        ResponseEntity<KakaoResponse.TokenDTO> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                KakaoResponse.TokenDTO.class);

        // 1.6 값 확인
        System.out.println("1-6토큰받기:"+ response.getBody().toString());

        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("Authorization", "Bearer "+response.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> request2 =
                new HttpEntity<>(headers2);

        ResponseEntity<KakaoResponse.KakaoUserDTO> response2 = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request2,
                KakaoResponse.KakaoUserDTO.class);

        KakaoResponse.KakaoUserDTO kakaoUserDTO = response2.getBody();

        //카카오로 부터 받은 개인 정보로 우리 서버에 회원가입을 했는지 검사한다.
        User user = userRepository.findByKakaoId(kakaoUserDTO.getId());
        if( user!=null ) {
            user.setNickname(kakaoUserDTO.getProperties().getNickname());//닉네임이 변경될 수 있으니 최신정보로 업데이트한다.
            return user;
        }
        else return kakaoUserDTO;
        //가입되어있으면 메인페이지로 이동
        //안되어 있으면 추가 정보를 받기 위해 회원가입페이지로 이동
    }
}

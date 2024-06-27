package com.example.project.user;

import com.example.project._core.enums.UserEnum;
import com.example.project._core.enums.UserProviderEnum;
import com.example.project._core.utils.EncryptUtil;
import com.example.project._core.utils.RuntimeConfiguration;
import com.example.project._core.utils.UserUtil;
import com.example.project.category_name.CategoryName;
import com.example.project.category_name.CategoryNameRepository;
import com.example.project.notification.NotificationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CategoryNameRepository categoryNameRepository;
    private final UserQueryRepository userQueryRepository;
    private final HttpSession session;
    private final RedisTemplate<String, Object> rt;

    public UserResponse.MainDTO mainPage(Integer userId) {
        List<Object[]> mySocialList = userQueryRepository.mySocialList(userId);

        List<Object[]> mySocialPopularList = userQueryRepository.mySocialPopularList(userId);

        List<Object[]> popularPostList = userQueryRepository.popularPostList();

        List<Object[]> socialCategoryList = userQueryRepository.socialCategoryList();

        List<CategoryName> categoryNameList = categoryNameRepository.findAll();

        return new UserResponse.MainDTO(mySocialList, mySocialPopularList, popularPostList, socialCategoryList, categoryNameList);
    }

    public UserResponse.MainAjaxDTO mainAjax(Integer categoryId) {
        List<Object[]> categorySocialList = userQueryRepository.categorySocialList(categoryId);

        return new UserResponse.MainAjaxDTO(categorySocialList);
    }


    //회원가입
    public void join(UserRequest.JoinDTO joinDTO) {
        User user = new User();
        user.setEmail(joinDTO.getEmail());

        user.setPassword(
                EncryptUtil.hashPw(joinDTO.getPassword())
        );
        user.setNickname(joinDTO.getName());

        user.setRole(joinDTO.getRole());

        user.setProvider(joinDTO.getProvider());
        user.setProviderId(joinDTO.getProviderId());

        LocalDate bod = LocalDate.parse(joinDTO.getBirthdate());
        user.setBirth(bod);
        userRepository.save(user);
    }

    //로그인
    public UserResponse.LoggedInUserDTO login(UserRequest.LoginDTO loginDTO) {
        String msg = "이메일 혹은 비밀번호가 일치하지 않습니다.";
        User user = userRepository.findByEmail(loginDTO.getEmail());

//        //해당 이메일로 사용자가 검색되지 않을 경우(없는 회원)
//        if(user == null) throw new RuntimeException("AAA:"+msg);

        //사용자가 입력한 비밀번호와 db에 저장된 비밀번호를 비교한다.
        //일치하는 경우
        if (EncryptUtil.checkPw(loginDTO.getPassword(), user.getPassword())) {
            return new UserResponse.LoggedInUserDTO(user);
        }
        //비밀번호 불일치
        else throw new RuntimeException("BBB:" + msg);
    }

    @Transactional
    public Object getKakaoId(String code) {

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
        System.out.println("1-6토큰받기:" + response.getBody().toString());

        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("Authorization", "Bearer " + response.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> request2 =
                new HttpEntity<>(headers2);

        ResponseEntity<KakaoResponse.KakaoUserDTO> response2 = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request2,
                KakaoResponse.KakaoUserDTO.class);

        KakaoResponse.KakaoUserDTO kakaoUserDTO = response2.getBody();
        System.out.println(response2.getBody());

        //카카오로 부터 받은 개인 정보로 우리 서버에 회원가입을 했는지 검사한다.
        User user = userRepository.findByIdAndProvider(UserProviderEnum.KAKAO, kakaoUserDTO.getId());
        System.out.println("user = " + user);
        if (user != null) {
            //데이터를 받은김에 db에도 동기화
            user.setNickname(kakaoUserDTO.getProperties().getNickname());//닉네임이 변경될 수 있으니 최신정보로 업데이트한다.
            return new UserResponse.LoggedInUserDTO(user);
        } else return kakaoUserDTO;
        //가입되어있으면 메인페이지로 이동
        //안되어 있으면 추가 정보를 받기 위해 회원가입페이지로 이동
    }

    @Transactional
    public Object getNaverId(String code, String state) {

        // 1.1 RestTemplate 설정
        RestTemplate rt = new RestTemplate();

        // 1.2 http header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 1.3 http body 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", RuntimeConfiguration.NAVER_CLIENT_ID);
        body.add("client_secret", RuntimeConfiguration.NAVER_CLIENT_SECRET);
        body.add("redirect_uri", RuntimeConfiguration.NAVER_REDIRECT_URL);
        body.add("code", code);
        body.add("state", state);

        // 1.4 body+header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        // 1.5 api 요청하기 (토큰 받기)
        ResponseEntity<NaverResponse.TokenDTO> response = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                request,
                NaverResponse.TokenDTO.class
        );

        // 1.6 값 확인
        System.out.println(response.getBody().toString());

        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("Authorization", "Bearer " + response.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> request2 =
                new HttpEntity<>(headers2);

        ResponseEntity<NaverResponse.NaverUserDTO> response2 = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                request2,
                NaverResponse.NaverUserDTO.class
        );

        NaverResponse.NaverUserDTO naverUserDTO = response2.getBody();
        System.out.println(response2.getBody());
        //카카오로 부터 받은 개인 정보로 우리 서버에 회원가입을 했는지 검사한다.
        User user = userRepository.findByIdAndProvider(UserProviderEnum.NAVER, naverUserDTO.getResponse().getId());
        if (user != null) {
            //데이터를 받은김에 db에도 동기화
            user.setNickname(naverUserDTO.getResponse().getName());//닉네임이 변경될 수 있으니 최신정보로 업데이트한다.
            return new UserResponse.LoggedInUserDTO(user);
        } else return naverUserDTO;
        //가입되어있으면 메인페이지로 이동
        //안되어 있으면 추가 정보를 받기 위해 회원가입페이지로 이동
    }

    // 회원 리스트 조회 (관리자)
    public UserResponse.UserListDTO getUserList() {
        Integer count = userRepository.findAllNormalUser();
        List<User> userListDTO = userRepository.findByRole(UserEnum.USER);

        List<UserResponse.UserListDTO.UserList> userList = userListDTO.stream()
                .map(UserResponse.UserListDTO.UserList::new)
                .collect(Collectors.toList());
        return new UserResponse.UserListDTO(count, userList);
    }

    // 회원 상세 조회 (관리자)
    public UserResponse.UserDetail getUserDetail(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        return new UserResponse.UserDetail(user);
    }

    @Transactional
    public UserResponse.LoggedInUserDTO updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isUpdated = false;

        if (updatedUser.getNickname() != null && !Objects.equals(updatedUser.getNickname(), existingUser.getNickname())) {
            existingUser.setNickname(updatedUser.getNickname());
            isUpdated = true;
        }

        if (updatedUser.getEmail() != null && !Objects.equals(updatedUser.getEmail(), existingUser.getEmail())) {
            existingUser.setEmail(updatedUser.getEmail());
            isUpdated = true;
        }

        if (updatedUser.getPassword() != null && !Objects.equals(updatedUser.getPassword(), existingUser.getPassword())) {
            existingUser.setPassword(
                    EncryptUtil.hashPw(updatedUser.getPassword())
            );
            isUpdated = true;
        }

        if (updatedUser.getBirth() != null && !Objects.equals(updatedUser.getBirth(), existingUser.getBirth())) {
            existingUser.setBirth(updatedUser.getBirth());
            isUpdated = true;
        }

        if (updatedUser.getPhone() != null && !Objects.equals(updatedUser.getPhone(), existingUser.getPhone())) {
            existingUser.setPhone(updatedUser.getPhone());
            isUpdated = true;
        }

        if (updatedUser.getImage() != null && !Objects.equals(updatedUser.getImage(), existingUser.getImage())) {
            existingUser.setImage(updatedUser.getImage());
            isUpdated = true;
        }


        if (isUpdated) {
            userRepository.save(existingUser);
        }
        return new UserResponse.LoggedInUserDTO(existingUser);
    }

    public void logout() {
        session.invalidate();
        rt.delete("sessionUser");
    }
}

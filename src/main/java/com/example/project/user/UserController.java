package com.example.project.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/test")
    public String test() {
        return "/social/fileaddForm";
    }


    @GetMapping("/")
    public String mainPage(HttpServletRequest request) {
        Integer userId = 1;
        UserResponse.MainDTO model = userService.mainPage(userId);
        request.setAttribute("model", model);

        return "main";
    }

    // 웹 시작화면 페이지
    @GetMapping("/user/notloggedinmain")
    public String socialAdd() {
        return "social/notLoggedinMain";
    }

    // 회원가입 메인 페이지
    @GetMapping("/user/joinMain")
    public String joinMain() {
        return "user/joinMainForm";
    }

    //회원가입 페이지 - 자체가입
    @GetMapping("/user/joinForm")
    public String joinForm(HttpServletRequest request){
        //kakaUser세션값을 날리기위해서 함. 이게있으면 닉네임을 세션에서 불러오고 readonly함.
        // 카카오 가입하기 했다가 이메일로 가입하기 누르면 값이 남아있는 문제갑라생함
        session.invalidate();
        return "user/joinForm";
    }

    //회원가입 페이지 - 카카오로 가입하기로 넘어온 사용자를 위한 URL
    @GetMapping("/user/joinForm/kakao")
    public String joinFormKakao(HttpServletRequest request){
        KakaoResponse.KakaoUserDTO kakaoUser =
                (KakaoResponse.KakaoUserDTO) session.getAttribute("kakaoUser");
        return "user/joinForm";
    }

    //회원가입 처리
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO){
        userService.join(joinDTO);
        return "redirect:/user/login";
    }

    //카카오로그인 콜백
    @GetMapping("/oauth/kakao/callback")
    public String oauthKakaoCallback(String code,HttpServletRequest request) {
        Object theUser = userService.findByKakaoId(code);

        if (theUser instanceof User) { //조회 결과: 이미 가입한 회원
            System.out.println((User) theUser);
            return "redirect:/";
        }
        if (theUser instanceof KakaoResponse.KakaoUserDTO) {   //조회 결과 : 아직 가입하지 않은 회원
            session.setAttribute("kakaoUser", theUser);
            return "redirect:/user/joinForm/kakao";
        }
        throw new RuntimeException("알 수 없는 오류가 발생했습니다.");
    }


    // 로그인 페이지
    @GetMapping("/user/login")
    public String loginForm() {
        UserResponse.LoggedInUserDTO user =
                (UserResponse.LoggedInUserDTO) session.getAttribute("user");
        if(user!=null) return "redirect:/"; //로그인되어있다면 메인페이지로 이동
        else return "user/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        UserResponse.LoggedInUserDTO loggedInUserDTO = userService.login(loginDTO);
        session.setAttribute("user",loggedInUserDTO);
//        UserResponse.LoggedInUserDTO loggedInUser = (UserResponse.LoggedInUserDTO)session.getAttribute("user");
        return "redirect:/";
    }

    // 로그아웃 처리
    @GetMapping("/user/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}

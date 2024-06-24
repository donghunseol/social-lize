package com.example.project.user;

import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.utils.UserUtil;
import com.example.project.notification.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;
    private final NotificationService notificationService;
    private final UserUtil userUtil;
    private final RedisTemplate<String, Object> rt;

    @GetMapping("/test")
    public String test() {
        return "/admin/social/socialCategoryDetailForm";
    }


    @GetMapping("/")
    public String mainPage(HttpServletRequest request) {
        Integer userId = 1;
        UserResponse.MainDTO model = userService.mainPage(userId);
        request.setAttribute("model", model);

        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        if(user == null) {
            return "redirect:/user/login"; //로그인되어있지 않으면 로그인페이지로 이동
        }
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
        //kakaoUser세션값을 날리기위해서 함. 이게있으면 닉네임을 세션에서 불러오고 readonly함.
        // 카카오 가입하기 했다가 이메일로 가입하기 누르면 값이 남아있는 문제갑라생함
        session.invalidate();
        return "user/joinForm";
    }

    //회원가입 페이지 - 카카오로 가입하기로 넘어온 사용자를 위한 URL
    @GetMapping("/user/joinForm/kakao")
    public String joinFormKakao(){
        return "user/joinForm";
    }

    //회원가입 페이지 - 카카오로 가입하기로 넘어온 사용자를 위한 URL
    @GetMapping("/user/joinForm/naver")
    public String joinFormNaver(){
        return "user/joinForm";
    }


    //회원가입 처리
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO){
        userService.join(joinDTO);
        return "redirect:/user/login";
    }

    //카카오로그인 콜백 & 카카오 로그인
    @GetMapping("/oauth/kakao/callback")
    public String oauthKakaoCallback(String code,HttpServletRequest request) {
        Object theUser = userService.getKakaoId(code);

        if (theUser instanceof UserResponse.LoggedInUserDTO ) { //조회 결과: 이미 가입한 회원 - 로그인처리
            userUtil.saveSessionUser((UserResponse.LoggedInUserDTO) theUser);

            return "redirect:/";
        }
        if (theUser instanceof KakaoResponse.KakaoUserDTO) {
            //조회 결과 : 아직 가입하지 않은 회원
            session.setAttribute("joinDTO", new JoinDTO((KakaoResponse.KakaoUserDTO) theUser));
            return "redirect:/user/joinForm/kakao";
        }
        throw new RuntimeException("알 수 없는 오류가 발생했습니다.");
    }

    //네이버 로그인 콜백 & 카카오 로그인
    @GetMapping("/oauth/naver/callback")
    public String oauthNaverCallback(String code,String state, HttpServletRequest request) {
        Object theUser = userService.getNaverId(code, state);

        if (theUser instanceof UserResponse.LoggedInUserDTO ) { //조회 결과: 이미 가입한 회원 - 로그인처리
            userUtil.saveSessionUser((UserResponse.LoggedInUserDTO) theUser);

            return "redirect:/";
        }
        if (theUser instanceof NaverResponse.NaverUserDTO) {   //조회 결과 : 아직 가입하지 않은 회원
            session.setAttribute("joinDTO", new JoinDTO((NaverResponse.NaverUserDTO) theUser));
            return "redirect:/user/joinForm/naver";
        }
        throw new RuntimeException("알 수 없는 오류가 발생했습니다.");
    }


    // 로그인 페이지
    @GetMapping("/user/login")
    public String loginForm() throws JsonProcessingException {
        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        if(user!=null) return "redirect:/"; //로그인되어있다면 메인페이지로 이동
        else return "user/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) throws JsonProcessingException {
        UserResponse.LoggedInUserDTO loggedInUserDTO = userService.login(loginDTO);
        //미확인 알림 개수 불러와서 dto에 저장하기
        Integer count = notificationService.getUnCheckedCountByUserId(loggedInUserDTO.getId());
        loggedInUserDTO.setUnCheckedNotifications(count);
        userUtil.saveSessionUser(loggedInUserDTO);
        return "redirect:/";
    }

    // 로그아웃 처리
    @GetMapping("/user/logout")
    public String logout() {
        session.invalidate();
        rt.delete("sessionUser");
        return "redirect:/user/notloggedinmain";
    }
}

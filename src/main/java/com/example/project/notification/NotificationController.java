package com.example.project.notification;

import com.example.project._core.enums.NotificationEnum;
import com.example.project._core.utils.UserUtil;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NotificationController {
    private final NotificationService notificationService;
    private final HttpSession session;
    private final UserUtil userUtil;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;


    //회원가입 페이지 - 자체가입
    @GetMapping("/notiTest")
    public String notiTest(){
        return "notificationTest/test";
    }

    private final SimpMessagingTemplate template;

//    @Autowired
//    public NotificationController(SimpMessagingTemplate template) {
//        this.template = template;
//    }

    //클라이언트로부터 직접 메세지를 받는다. (예:읽음처리)
    @MessageMapping("/notify")
    public String receivedMsgFromClient(String msg){
        System.out.println(msg);
        return msg;
    }

    // 웹 요청을 통해 메시지를 보낼 수 있는 간단한 API
    // 테스트용
    @GetMapping("/send")
    public @ResponseBody String sendNoticeNotification(@RequestParam(value = "count", defaultValue = "5") String count) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        // 공지글을 작성하면 모든 사용자의 알림을 생성한다. (관리자 공지사항 등록시 아래 로직을 추가한다)
        //1. 모든 사용자의 목록 불러오기
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            Notification notification =
                    new Notification().builder().
                            userId(user).
                            role(NotificationEnum.BOARD).
                            checked(false).build();
            //2. 알림 내용 db에 저장
            notificationRepository.save(notification);
        }
        //3. 공지 등록 채널에 알림 발송
        template.convertAndSend("/topic/notice", "");
        return "";
    }

    @GetMapping("/notification/get")
    public @ResponseBody NotificationResponse.ListDTO getNotice() {
        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        NotificationResponse.ListDTO notiList = notificationService.getAllByUserId(user.getId());
        System.out.println("notiList = " + notiList);
//        Integer countToSend = user.getUnCheckedNotifications();
        return notiList;
    }

    @GetMapping("/notification/get/unChecked")
    public @ResponseBody Integer getUnCheckedCount() {
        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        return notificationService.getUnCheckedCountByUserId(user.getId());
    }

    @GetMapping("/notification/set/allChecked")
    public @ResponseBody String setAllChecked() {
        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        notificationService.updateAllCheckedByUserId(user.getId());
        return "";
    }
}


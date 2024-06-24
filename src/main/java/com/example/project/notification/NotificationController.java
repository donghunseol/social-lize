package com.example.project.notification;

import com.example.project._core.utils.UserUtil;
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
        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        //현재 읽지 않은 알림의 개수를 불러온다.
        Integer countToSend = notificationService.getUnCheckedCountByUserId(user.getId());
//        Integer countToSend = user.getUnCheckedNotifications();

        //현재 읽지 않은 알림 개수 + 1한 값을 메세지로 보낸다.
        template.convertAndSend("/topic/notice", countToSend+1);
        System.out.println(countToSend);
        return "Sended : "+countToSend;
    }

    @GetMapping("/get")
    public @ResponseBody NotificationResponse.ListDTO getNotice() {
        UserResponse.LoggedInUserDTO user = userUtil.getSessionUser();
        NotificationResponse.ListDTO notiList = notificationService.getAllByUserId(user.getId());
        System.out.println("notiList = " + notiList);
//        Integer countToSend = user.getUnCheckedNotifications();
        return notiList;
    }
}


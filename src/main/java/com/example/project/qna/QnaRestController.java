package com.example.project.qna;

import com.example.project._core.utils.ApiUtil;
import com.example.project._core.utils.UserUtil;
import com.example.project.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QnaRestController {
    private final QnaService qnaService;
    private final UserUtil userUtil;

    // 문의 작성
    @PostMapping("/qna")
    public ResponseEntity<?> save(@ModelAttribute QnaRequest.SaveDTO reqDTO) {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        qnaService.save(sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @GetMapping("/qna/my/answer/list")
    public ResponseEntity<?> answerList() {
        System.out.println("나 호출되었어");
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        QnaResponse.QnaAnswerListDTO modal = qnaService.answerList(sessionUser.getId());

//        System.out.println(modal.getCount());
//        System.out.println(modal.getList().get(0).getContent());

        return ResponseEntity.ok(new ApiUtil<>(modal));
    }

    @GetMapping("/qna/my/waiting/list")
    public ResponseEntity<?> waitingList() {
        UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
        QnaResponse.QnaWaitingListDTO modal = qnaService.waitingList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(modal));
    }
}

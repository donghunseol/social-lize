package com.example.project.social;

import com.example.project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SocialRestController {
    private final SocialService socialService;
    private final HttpSession session;

    // 소셜 생성
    @PostMapping("/social/create")
    public ResponseEntity<?> create(@RequestBody SocialRequest.Create CreateDTO) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialService.createSocial(CreateDTO);
        return ResponseEntity.ok(new ApiUtil<>(CreateDTO));
    }
}
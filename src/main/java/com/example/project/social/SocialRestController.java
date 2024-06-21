package com.example.project.social;

import com.example.project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 소셜 수정
    @PutMapping("/social/update/{socialId}")
    public ResponseEntity<?> update(@PathVariable Integer socialId, @RequestBody SocialRequest.Update UpdateDTO) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialService.updateSocial(socialId, UpdateDTO);
        return ResponseEntity.ok(new ApiUtil<>(UpdateDTO));
    }

    // 소셜 삭제
    @PutMapping("/social/delete/{socialId}")
    public ResponseEntity<?> delete(@PathVariable Integer socialId) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialService.deleteSocial(socialId);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

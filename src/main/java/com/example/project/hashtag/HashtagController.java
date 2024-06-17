package com.example.project.hashtag;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HashtagController {
    private final HashtagService hashtagService;
    private final HttpSession session;
}

package com.example.project.hashtag;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class HashtagController {
    private final HashtagService hashtagService;
    private final HttpSession session;
}

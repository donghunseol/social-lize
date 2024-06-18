package com.example.project.bookmark;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final HttpSession session;
}

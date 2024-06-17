package com.example.project.bookmark;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final HttpSession session;
}

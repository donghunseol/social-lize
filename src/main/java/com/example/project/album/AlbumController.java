package com.example.project.album;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AlbumController {
    private final AlbumService albumService;
    private final HttpSession session;
}

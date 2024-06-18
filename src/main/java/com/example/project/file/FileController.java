package com.example.project.file;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileService fileService;
    private final HttpSession session;
}

package com.example.project.file;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
    private final HttpSession session;
}

package com.example.project.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
}

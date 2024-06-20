package com.example.project.file;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    @Transactional
    public void save(File file) {
        fileRepository.save(file);
    }
}

package com.example.project.album;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
}

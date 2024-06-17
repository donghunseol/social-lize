package com.example.project.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
}

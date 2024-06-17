package com.example.project.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
}

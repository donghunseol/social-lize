package com.example.project.social;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialService {
    private final SocialRepository socialRepository;
}

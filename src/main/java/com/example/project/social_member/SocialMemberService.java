package com.example.project.social_member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialMemberService {
    private final SocialMemberRepository socialMemberRepository;
}

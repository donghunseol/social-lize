package com.example.project.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
}

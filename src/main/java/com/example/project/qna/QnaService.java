package com.example.project.qna;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QnaService {
    private final QnaRepository qnaRepository;
}

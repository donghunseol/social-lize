package com.example.project.qna;

import com.example.project._core.enums.QnaEnum;
import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QnaService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    public QnaResponse.QnaAnswerListDTO answerList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        List<Qna> qnaList = qnaRepository.findByQnaAnswer(user.getId());

        return new QnaResponse.QnaAnswerListDTO(qnaList, qnaList.size());
    }

    public QnaResponse.QnaWaitingListDTO waitingList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        List<Qna> qnaList = qnaRepository.findByQnaWaiting(user.getId());

        return new QnaResponse.QnaWaitingListDTO(qnaList, qnaList.size());
    }

    @Transactional
    public void save(Integer userId, QnaRequest.SaveDTO reqDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        qnaRepository.save(reqDTO.toEntity(user));
    }

    @Transactional
    public void update(Integer qnaId, Integer userId, QnaRequest.UpdateDTO reqDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        Qna qna = qnaRepository.findByQnaIdAndWaiting(qnaId, QnaEnum.WAITING);

        if (!user.getId().equals(qna.getUserId().getId())) {
            throw new Exception403("문의 사항을 수정할 권한이 없습니다.");
        }

        qna.setTitle(reqDTO.getTitle());
        qna.setContent(reqDTO.getContent());
    }

    @Transactional
    public void delete(Integer qnaId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        Qna qna = qnaRepository.findByQnaIdAndWaiting(qnaId, QnaEnum.WAITING);

        if (!user.getId().equals(qna.getUserId().getId())) {
            throw new Exception403("문의 사항을 삭제할 권한이 없습니다.");
        }

        qna.setState(QnaEnum.DELETE);
    }


    public QnaResponse.QnaDetailDTO detail(Integer userId, Integer qnaId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요합니다."));

        Qna qna = qnaRepository.findByQnaId(qnaId);

        if (!user.getId().equals(qna.getUserId().getId())) {
            throw new Exception403("문의 사항을 열람할 권한이 없습니다.");
        }

        if (qna.getState().equals(QnaEnum.DELETE)) {
            throw new Exception404("문의 사항을 삭제하여 조회할 수 없습니다.");
        }

        return new QnaResponse.QnaDetailDTO(qna);
    }

    // 문의 리스트 조회 (관리자)
    public QnaResponse.QnaListAndCount getQnaListAndCount() {
        Integer qnaCount = qnaRepository.findAllQnaCount();
        List<Qna> qnaListDTO = qnaRepository.findAllQnaList();

        // Qna 객체를 QnaList 객체로 변환
        List<QnaResponse.QnaListAndCount.QnaList> qnaList = qnaListDTO.stream()
                .map(QnaResponse.QnaListAndCount.QnaList::new)
                .collect(Collectors.toList());
        return new QnaResponse.QnaListAndCount(qnaList, qnaCount);
    }

    // 문의 상세 조회 (관리자)
    public QnaResponse.QnaDetail getQnaDetail(Integer qnaId) {
        Qna qna = qnaRepository.findQnaAndUserByQnaId(qnaId)
                .orElseThrow(() -> new Exception404("해당 문의 사항은 존재하지 않습니다."));

        return new QnaResponse.QnaDetail(qna);
    }

    // 문의 답변하기
    @Transactional
    public void replyQna(Integer qnaId, QnaRequest.replyDTO replyDTO) {
        Qna qna = qnaRepository.findQnaAndUserByQnaId(qnaId)
                .orElseThrow(() -> new Exception404("해당 문의 사항은 존재하지 않습니다."));
        qna.setReplyContent(replyDTO.getReplyContent());
        qna.setReplyCreatedAt(LocalDateTime.now());
        qna.setState(QnaEnum.ANSWER);
        qnaRepository.save(qna);
    }
}

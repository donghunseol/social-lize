package com.example.project.notice;

import com.example.project._core.enums.BoardEnum;

import com.example.project._core.enums.DeleteStateEnum;

import com.example.project._core.enums.NotificationEnum;

import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project.board.Board;
import com.example.project.board.BoardRepository;
import com.example.project.notification.Notification;
import com.example.project.notification.NotificationRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NotificationRepository notificationRepository;
    private final NoticeRepository noticeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate template;

    // 공지 리스트 조회 (관리자)
    public NoticeResponse.NoticeDTO getNoticeListAndCount() {
        Integer count = noticeRepository.findAllNoticeCountByState(DeleteStateEnum.ACTIVE);
        List<Notice> noticeDTO = noticeRepository.findAllByState(DeleteStateEnum.ACTIVE);

        List<NoticeResponse.NoticeDTO.ListDTO> noticeList = noticeDTO.stream()
                .map(NoticeResponse.NoticeDTO.ListDTO::new).toList();

        return new NoticeResponse.NoticeDTO(count, noticeList);
    }

    // 공지 상세 조회 (관리자)
    public NoticeResponse.DetailDTO getNoticeDetail(Integer noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new Exception404("해당 공지가 없습니다."));
        return new NoticeResponse.DetailDTO(notice);
    }

    // 공지 작성 (관리자)
    @Transactional
    public void createNotice(Integer userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("해당 작업에 대한 권한이 없습니다."));

        // 공지 게시글 생성
        Board board = Board.builder()
                .userId(user)
                .content(content)
                .role(BoardEnum.NOTICE)
                .state(DeleteStateEnum.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        boardRepository.save(board);

        // 공지 생성
        Notice notice = Notice.builder()
                .board(board)
                .state(DeleteStateEnum.ACTIVE)
                .build();

        noticeRepository.save(notice);


        // 공지사항 등록 알림 발송 로직
        //1. 모든 사용자의 목록 불러오기
        List<User> userList = userRepository.findAll();
        for(User userToSend : userList){
            Notification notification =
                    new Notification().builder().
                            userId(userToSend).
                            role(NotificationEnum.BOARD).
                            checked(false).build();
            //2. 알림 내용 db에 저장
            notificationRepository.save(notification);
        }
        //3. 공지 등록 채널에 알림 발송
        template.convertAndSend("/topic/notice", "");
    }

    // 공지 삭제 (관리자)
    @Transactional
    public void deleteNotice(Integer noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new Exception404("해당 공지가 없습니다."));
        Board board = boardRepository.findById(notice.getBoard().getId())
                .orElseThrow(() -> new Exception404("해당 게시글이 없습니다."));

        board.setState(DeleteStateEnum.DELETED);
        notice.setState(DeleteStateEnum.DELETED);

        boardRepository.save(board);
        noticeRepository.save(notice);
    }
}

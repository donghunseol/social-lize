package com.example.project.notice;

import com.example.project._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    // 공지 리스트 조회 (관리자)
    public NoticeResponse.NoticeDTO getNoticeListAndCount() {
        Integer count = noticeRepository.findAllNoticeCount();
        List<Notice> noticeDTO = noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

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
}

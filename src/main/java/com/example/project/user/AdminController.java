package com.example.project.user;

import com.example.project.board.BoardResponse;
import com.example.project.board.BoardService;
import com.example.project.category_name.CategoryNameResponse;
import com.example.project.category_name.CategoryNameService;
import com.example.project.notice.NoticeResponse;
import com.example.project.notice.NoticeService;
import com.example.project.qna.QnaRequest;
import com.example.project.qna.QnaResponse;
import com.example.project.qna.QnaService;
import com.example.project.report.ReportResponse;
import com.example.project.report.ReportService;
import com.example.project.social.SocialResponse;
import com.example.project.social.SocialService;
import com.example.project.social_member.SocialMemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
    private final SocialService socialService;
    private final SocialMemberService socialMemberService;
    private final CategoryNameService categoryNameService;
    private final UserService userService;
    private final BoardService boardService;
    private final QnaService qnaService;
    private final ReportService reportService;
    private final NoticeService noticeService;

    // 회원 리스트 조회
    @GetMapping({"/", "/user-list"})
    public String userListPage(HttpServletRequest request) {
        UserResponse.UserListDTO userListDTO = userService.getUserList();
        request.setAttribute("userListDTO", userListDTO);
        return "admin/user/userListForm";
    }

    // 회원 상세 조회
    @GetMapping("/user/{userId}/detail")
    public String userDetailPage(HttpServletRequest request, @PathVariable Integer userId) {
        UserResponse.UserDetail userDetail = userService.getUserDetail(userId);
        request.setAttribute("userDetail", userDetail);
        return "admin/user/userDetailForm";
    }

    @PostMapping("/user/{userId}/delete")
    public String deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/user-list";
    }

    // 카테고리 리스트 조회
    @GetMapping("/category-name-list")
    public String categoryNameListPage(HttpServletRequest request) {
        CategoryNameResponse.CategoryListDTO categoryListDTO = categoryNameService.getCategoryList();
        request.setAttribute("categoryListDTO", categoryListDTO);
        return "admin/social/socialCategoryListForm";
    }

    // 카테고리 상세 조회
    @GetMapping("/category-name/{categoryNameId}")
    public String categoryNameDetailPage(HttpServletRequest request, @PathVariable Integer categoryNameId) {
        CategoryNameResponse.Detail categoryDetail = categoryNameService.getCategoryDetail(categoryNameId);
        request.setAttribute("categoryDetail", categoryDetail);
        return "admin/social/socialCategoryDetailForm";
    }

    // 소셜 리스트 조회
    @GetMapping("/social-list")
    public String socialListPage(HttpServletRequest request) {
        SocialResponse.SocialListDTO socialListDTO = socialService.getSocialList();
        request.setAttribute("socialListDTO", socialListDTO);
        return "admin/social/socialListForm";
    }

    // 소셜 상세 조회
    @GetMapping("/social/{socialId}")
    public String socialDetailPage(HttpServletRequest request, @PathVariable Integer socialId) {
        SocialResponse.DetailDTO socialDetail = socialService.getSocialDetail(socialId);
        request.setAttribute("socialDetail", socialDetail);
        return "admin/social/socialDetailForm";
    }

    // 게시글 리스트 조회
    @GetMapping("/board-list")
    public String boardListPage(HttpServletRequest request) {
        BoardResponse.BoardListDTO boardListDTO = boardService.getBoardList();
        request.setAttribute("boardListDTO", boardListDTO);
        return "admin/board/boardListForm";
    }

    // 게시글 상세 조회
    @GetMapping("/board/{boardId}")
    public String boardDetailPage(HttpServletRequest request, @PathVariable Integer boardId) {
        BoardResponse.Detail boardDetail = boardService.getBoardDetail(boardId);
        request.setAttribute("boardDetail", boardDetail);
        return "admin/board/boardDetailForm";
    }

    // 문의 리스트 조회
    @GetMapping("/qna-list")
    public String qnaListPage(HttpServletRequest request) {
        QnaResponse.QnaListAndCount qnaListAndCount = qnaService.getQnaListAndCount();
        request.setAttribute("qnaListAndCount", qnaListAndCount);
        return "admin/management/qnaListForm";
    }

    // 문의 상세 조회
    @GetMapping("/qna/{qnaId}")
    public String qnaDetailPage(HttpServletRequest request, @PathVariable Integer qnaId) {
        QnaResponse.QnaDetail qnaDetail = qnaService.getQnaDetail(qnaId);
        request.setAttribute("qnaDetail", qnaDetail);
        return "admin/management/qnaDetailForm";
    }

    // 문의 답변하기
    @PostMapping("/qna/{qnaId}/reply")
    public String ReplyPage(@PathVariable Integer qnaId, QnaRequest.replyDTO replyDTO) {
        qnaService.replyQna(qnaId, replyDTO);
        return "redirect:/admin/qna-list";
    }

    // 신고 리스트 조회
    @GetMapping("/report-list")
    public String reportListPage(HttpServletRequest request) {
        ReportResponse.ReportDTO reportDTO = reportService.getReportList();
        request.setAttribute("reportDTO", reportDTO);
        return "admin/management/reportListForm";
    }

    // 신고 상세 조회
    @GetMapping("/report/{reportId}")
    public String reportDetailPage(HttpServletRequest request, @PathVariable Integer reportId) {
        return "admin/management/reportDetailForm";
    }

    // 공지 리스트 조회
    @GetMapping("/notice-list")
    public String noticeListPage(HttpServletRequest request) {
        NoticeResponse.NoticeDTO noticeDTO = noticeService.getNoticeListAndCount();
        request.setAttribute("noticeDTO", noticeDTO);
        return "admin/management/noticeListForm";
    }

    // 공지 상세 조회
    @GetMapping("/notice/{noticeId}")
    public String noticeDetailPage(HttpServletRequest request, @PathVariable Integer noticeId) {
        NoticeResponse.DetailDTO noticeDetail = noticeService.getNoticeDetail(noticeId);
        request.setAttribute("noticeDetail", noticeDetail);
        return "admin/management/noticeDetailForm";
    }

    // 공지 작성 페이지
    @GetMapping("/notice/write-form")
    public String noticeWritePage(HttpServletRequest request) {
        return "admin/management/noticeWriteForm";
    }

    // 공지 등록
    @PostMapping("/notice/write")
    public String noticeWrite(String content) {
        Integer userId = 1;
        noticeService.createNotice(userId, content);
        return "redirect:/admin/notice-list";
    }

    // 공지 삭제
    @PostMapping("/notice/{noticeId}/delete")
    public String noticeDelete(@PathVariable Integer noticeId) {
        noticeService.deleteNotice(noticeId);
        return "redirect:/admin/notice-list";
    }

    // 공지 수정 페이지
    @GetMapping("/notice/{noticeId}/update-form")
    public String noticeUpdatePage(HttpServletRequest request, @PathVariable Integer noticeId) {
        NoticeResponse.DetailDTO noticeDetail = noticeService.getNoticeDetail(noticeId);
        request.setAttribute("noticeDetail", noticeDetail);
        return "admin/management/noticeUpdateForm";
    }

    // 공지 수정
    @PostMapping("/notice/{noticeId}/update")
    public String noticeUpdate(@PathVariable Integer noticeId, String content) {
        noticeService.updateNotice(noticeId, content);
        return "redirect:/admin/notice-list";
    }
}

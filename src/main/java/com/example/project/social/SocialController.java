package com.example.project.social;

import com.example.project.board.BoardResponse;
import com.example.project.board.BoardService;
import com.example.project.file.FileRequest;
import com.example.project.file.FileService;
import com.example.project.user.User;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.example.project._core.utils.UserUtil.getLoggedInUser;


@RequiredArgsConstructor
@Controller
public class SocialController {
    private final SocialService socialService;
    private final BoardService boardService;
    private final FileService fileService;
    private final HttpSession session;

    @GetMapping("/social/detail/{socialId}")
    public String socialDetail(@PathVariable int socialId, HttpServletRequest request) {
        BoardResponse.BoardListDTO boardList = boardService.boardList(socialId, 1);
        request.setAttribute("boardList", boardList);
        return "social/detail";
    }

    // 가입하지 않은 소셜 둘러보기 페이지
    @GetMapping("/social/notJoined")
    public String socialNotJoin() {
        return "social/notJoinedForm";
    }

    // 새 소셜 추가하기 페이지
    @GetMapping("/social/socialAdd")
    public String socialAdd() {
        return "social/socialaddForm";
    }

    // 서랍 페이지
    @GetMapping("/social/fileadd/{socialId}")
    public String fileAdd(@PathVariable Integer socialId, HttpServletRequest request, FileRequest.FileUploadDTO reqDTO) {
        // 파일 업로드 시 저장
        UserResponse.LoggedInUserDTO sessionUser = (UserResponse.LoggedInUserDTO) session.getAttribute("sessionUser");
        fileService.fileUpload(reqDTO, sessionUser.getId(), socialId);

        // 페이지에 뿌릴 데이터
        SocialResponse.AlbumAndFileListDTO respDTO = socialService.getSocialAlbumList(socialId);
        request.setAttribute("models", respDTO);

        return "social/fileaddForm";
    }
}
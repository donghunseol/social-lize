package com.example.project.social;

import com.example.project.album.AlbumRequest;
import com.example.project.album.AlbumResponse;
import com.example.project.album.AlbumService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SocialController {
    private final SocialService socialService;
    private final HttpSession session;

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
    public String fileAdd(@PathVariable Integer socialId, HttpServletRequest request) {
        SocialResponse.albumAndFileListDTO respDTO = socialService.getSocialAlbumList(socialId);
        request.setAttribute("models", respDTO);

        return "social/fileaddForm";
    }
}

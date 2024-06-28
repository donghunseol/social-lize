package com.example.project.social;

import com.example.project._core.utils.ApiUtil;
import com.example.project._core.utils.UserUtil;
import com.example.project.file.FileRequest;
import com.example.project.file.FileService;
import com.example.project.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class SocialRestController {
    private final SocialService socialService;
    private final FileService fileService;
    private final UserUtil userUtil;
    private final HttpSession session;

    // 소셜 생성
    @PostMapping("/social/create")
    public ResponseEntity<?> create(@RequestBody SocialRequest.Create CreateDTO) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialService.createSocial(CreateDTO);
        return ResponseEntity.ok(new ApiUtil<>(CreateDTO));
    }

    // 소셜 수정
    @PutMapping("/social/update/{socialId}")
    public ResponseEntity<?> update(@PathVariable Integer socialId, @RequestBody SocialRequest.Update UpdateDTO) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialService.updateSocial(socialId, UpdateDTO);
        return ResponseEntity.ok(new ApiUtil<>(UpdateDTO));
    }

    // 소셜 삭제
    @PutMapping("/social/delete/{socialId}")
    public ResponseEntity<?> delete(@PathVariable Integer socialId) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        socialService.deleteSocial(socialId);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }


    // 파일 추가
    @PostMapping("/social/file/upload/{socialId}")
    @ResponseBody
    public ResponseEntity<String> fileUpdate(@PathVariable Integer socialId, @RequestParam("file") MultipartFile file) {
        try {
            UserResponse.LoggedInUserDTO sessionUser = userUtil.getSessionUser();
            FileRequest.FileUploadDTO reqDTO = new FileRequest.FileUploadDTO();
            reqDTO.setFile(file);
            reqDTO.setSocialId(socialId);

            fileService.fileUpload(reqDTO, sessionUser.getId(), socialId);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/social/fileadd/reload/{socialId}")
    public ResponseEntity<SocialResponse.AlbumAndFileListDTO> getFileAddInfo(@PathVariable Integer socialId) {
        SocialResponse.AlbumAndFileListDTO respDTO = socialService.getSocialAlbumList(socialId);
        return ResponseEntity.ok(respDTO);
    }
}

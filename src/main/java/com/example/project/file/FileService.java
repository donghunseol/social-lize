package com.example.project.file;

import com.example.project._core.enums.SocialMemberStateEnum;
import com.example.project._core.errors.exception.Exception400;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception404;
import com.example.project._core.utils.FileUtil;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.social_member.SocialMember;
import com.example.project.social_member.SocialMemberRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final SocialRepository socialRepository;
    private final SocialMemberRepository socialMemberRepository;

    // 파일 업로드
    @Transactional
    public void fileUpload(FileRequest.FileUploadDTO reqDTO , Integer userId, Integer socialId) {
        // 인증 처리
        User sessionUser = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재하지 않는 유저입니다"));
        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception404("존재하지 않는 소셜입니다"));
        SocialMember socialMember = socialMemberRepository.findByUserIdAndSocialId(userId, socialId)
                .orElseThrow(() -> new Exception401("해당 소셜의 멤버가 아닙니다"));

        if (socialMember.getState() != SocialMemberStateEnum.APPROVED){
            throw new Exception401("파일을 첨부할 권한이 없습니다");
        }

        // 파일 업로드
        String filePath = null;
        String fileName = null;

        if(reqDTO.getFile() != null){
            FileUtil.FileUploadResult uploadResult = FileUtil.uploadFile("./upload", reqDTO.getFile());
            filePath = uploadResult.getFilePath();
            fileName = uploadResult.getFileName();
            // 업로드 결과 검증
            if (filePath == null || fileName == null) {
                throw new Exception400("파일 업로드 실패");
            }
        } else {
            throw new Exception400("업로드할 파일이 없습니다");
        }


        // 파일 등록
        File file = File.builder()
                .socialId(social)
                .userId(sessionUser)
                .name(fileName)
                .path(filePath)
                .build();

        fileRepository.save(file);
    }
}

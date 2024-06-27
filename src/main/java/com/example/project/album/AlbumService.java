package com.example.project.album;

import com.example.project._core.utils.HlsUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Transactional
    public void videoConvert(Integer albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("해당 앨범이 존재하지 않습니다"));

        try {
            // 비디오를 HLS로 변환하는 메서드 호출
            HlsUtil.convertHls(album.getPath());

            // 변환된 HLS 파일의 리소스를 가져와서 저장
            Resource hlsResource = HlsUtil.getHlsResource(album.getPath());

            // 예시: 앨범 엔티티에 변환된 HLS 리소스 경로를 저장
            album.setHlsPath(album.getPath() + ".m3u8");

            // 앨범 엔티티 저장
            albumRepository.save(album);
        } catch (Exception e) {
            throw new RuntimeException("앨범 ID " + albumId + "의 비디오를 HLS로 변환하는 중 오류 발생: " + e.getMessage(), e);
        }
    }


    // HLS 변환 상태를 확인
    public boolean isHlsReady(Integer albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("해당 앨범이 존재하지 않습니다"));

        return album.getHlsPath() != null;
    }
}

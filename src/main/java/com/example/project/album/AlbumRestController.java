package com.example.project.album;

import com.example.project._core.utils.HlsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AlbumRestController {

    private final AlbumService albumService;

    @GetMapping("/convert/{albumId}")
    public ResponseEntity<?> convert(@PathVariable Integer albumId){
        // HLS 변환 서비스 메서드 호출
        albumService.videoConvert(albumId);

        // 변환 완료를 응답으로 처리
        return ResponseEntity.ok().build();
    }

    //  HLS 변환 상태를 확인
    @GetMapping("/album/hls-status/{albumId}")
    public ResponseEntity<Map<String, Boolean>> getHlsStatus(@PathVariable Integer albumId) {
        boolean isHlsReady = albumService.isHlsReady(albumId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("HLS 변환 완료", isHlsReady);

        return ResponseEntity.ok(response);
    }

    // HLS 플레이리스트 요청 처리
    @GetMapping("/hls/{hlsName}.m3u8")
    public ResponseEntity<Resource> getHls(@PathVariable String hlsName) {
        // HLS 플레이리스트 리소스를 서비스로부터 가져옴
        Resource resource = null;
        try {
            resource = HlsUtil.getHlsResource(hlsName + ".m3u8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        // 다운로드 시 파일명을 명시적으로 지정
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + hlsName + ".m3u8");
        // HLS 플레이리스트의 Content-Type 설정
        headers.setContentType(MediaType.parseMediaType("application/vnd.apple.mpegurl"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    // HLS 세그먼트 요청 처리
    @GetMapping("/hls/{tsName}.ts")
    public ResponseEntity<Resource> getHlsTs(@PathVariable String tsName) {
        // HLS 세그먼트 파일명을 지정하여 리소스를 서비스로부터 가져옴
        tsName = tsName + ".ts";
        Resource resource = null;
        try {
            resource = HlsUtil.getHlsResource(tsName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        // 다운로드 시 파일명을 동적으로 설정
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + tsName);
        // HLS 세그먼트의 Content-Type 설정
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}

package com.example.project.social;

import com.example.project.album.Album;
import com.example.project.album.AlbumRepository;
import com.example.project.album.AlbumResponse;
import com.example.project.file.File;
import com.example.project.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SocialService {
    private final SocialRepository socialRepository;
    private final AlbumRepository albumRepository;
    private final FileRepository fileRepository;

    // 소셜 별 앨범, 파일 리스트 출력
    public SocialResponse.AlbumAndFileListDTO getSocialAlbumList(Integer socialId){

        // 소셜 별 앨범 리스트 가져오기
        List<Album> albumList = albumRepository.findBySocialId(socialId);
        List<File> fileList = fileRepository.findBySocialId(socialId);

        // 소셜에 앨범이 비었을 때 null 을 반환
        if (albumList == null){
            albumList = Collections.emptyList();
        }

        // 소셜에 파일이 비었을 때 null 을 반환
        if (fileList == null){
            fileList = Collections.emptyList();
        }

        // 앨범, 파일 리스트 DTO 담기
        return new SocialResponse.AlbumAndFileListDTO(socialId, albumList, fileList);
    }
}

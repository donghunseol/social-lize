package com.example.project.social;

import com.example.project._core.enums.AlbumEnum;
import com.example.project.album.Album;
import com.example.project.file.File;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class SocialResponse {

    @Data
    public static class AlbumAndFileListDTO {
        private Integer socialId; // 어디 소속된 앨범인지 확인
        private List<AlbumDTO> albums;
        private List<FileDTO> files;

        public AlbumAndFileListDTO(Integer socialId, List<Album> albumList, List<File> fileList) {
            this.socialId = socialId;
            this.albums = albumList.stream()
                    .map(AlbumDTO::new).collect(Collectors.toList());
            this.files = fileList.stream()
                    .map(FileDTO::new).collect(Collectors.toList());
        }

        @Data
        static class AlbumDTO {
            private String path;
            private AlbumEnum type;
            private boolean isVideo;
            private boolean isImage;

            public AlbumDTO(Album album) {
                this.path = album.getPath();
                this.type = album.getType();
                this.isVideo = (this.type == AlbumEnum.VIDEO);
                this.isImage = (this.type == AlbumEnum.IMAGE);
            }
        }

        @Data
        static class FileDTO {
            private String name;
            private String path;

            public FileDTO(File file) {
                this.name = file.getName();
                this.path = file.getPath();
            }
        }
    }

    // 소셜 리스트 DTO
    @Data
    public static class SocialListDTO {
        private Integer count;
        private List<SocialList> socialList;

        public SocialListDTO(Integer count, List<SocialList> socialList) {
            this.count = count;
            this.socialList = socialList;
        }

        @AllArgsConstructor
        @Data
        public static class SocialList {
            private Integer id;
            private String name;
            private List<String> categories;
            private Integer memberCount;
            private String createdAt;
        }
    }


    // 소셜 상세 DTO
    @AllArgsConstructor
    @Data
    public static class Detail {
        private Integer id;
        private String name;
        private String image;
        private String info;
        private List<String> categories;
        private Integer memberCount;
        private String createdAt;
    }
}

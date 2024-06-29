package com.example.project.social;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.enums.SocialMemberRoleEnum;
import com.example.project._core.enums.SocialMemberStateEnum;
import com.example.project._core.utils.LocalDateTimeFormatter;
import com.example.project.album.Album;
import com.example.project.category.Category;
import com.example.project.file.File;
import com.example.project.social_member.SocialMember;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SocialResponse {

    @Data
    public static class MyApplySocialListDTO {
        private List<SocialListDTO> socialList;

        public MyApplySocialListDTO(List<SocialMember> socialList, List<Integer> memberCount) {
            this.socialList = IntStream.range(0, socialList.size())
                    .mapToObj(i -> new SocialListDTO(socialList.get(i), memberCount.get(i)))
                    .toList();
        }

        @Data
        public static class SocialListDTO {
            private Integer id;
            private String image;
            private String name;
            private String state;
            private Integer memberCount;

            public SocialListDTO(SocialMember social, Integer memberCount) {
                this.id = social.getSocialId().getId();
                this.image = social.getSocialId().getImage();
                this.name = social.getSocialId().getName();

                this.state = switch (social.getState()) {
                    case WAITING -> "승인 대기중";
                    case REFUSE -> "가입 거절됨";
                    default -> "없음";
                };

                this.memberCount = memberCount;
            }
        }
    }

    @Data
    public static class AlbumAndFileListDTO {
        private Integer socialId; // 어디 소속된 앨범인지 확인
        private List<AlbumDTO> albums;
        private List<FileDTO> files;

        // 사이드바 데이터를 담기위해 추가
        private String title;
        private String name;
        private Integer memberCount;
        private String info;
        private String image;

        public AlbumAndFileListDTO(Social social, List<Album> albumList, List<File> fileList, Integer memberCount, String memberLeader) {
            this.socialId = social.getId();

            // 사이드바 데이터
            this.title = social.getName();
            this.name = memberLeader;
            this.memberCount = memberCount;
            this.info = social.getInfo();
            this.image = social.getImage();

            this.albums = albumList.stream()
                    .map(AlbumDTO::new).collect(Collectors.toList());
            this.files = fileList.stream()
                    .map(FileDTO::new).collect(Collectors.toList());
        }

        @Data
        static class AlbumDTO {
            private String path;
            private String hlsPath;
            private AlbumEnum type;
            private boolean isVideo;

            public AlbumDTO(Album album) {
                this.path = album.getPath();
                this.hlsPath = album.getHlsPath();
                this.type = album.getType();
                // AlbumEnum의 getType() 메서드가 어떤 값을 반환하는지 확인 후 설정
                if (this.type != null) {
                    if (album.getType() == AlbumEnum.VIDEO){
                        this.isVideo = true;
                    } else {
                        this.isVideo = false;
                    }
                } else {
                    this.isVideo = false;
                }
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
    @Data
    public static class DetailDTO {
        private Detail detail;
        private Integer memberCount;
        private List<SocialMemberList> socialMemberList;

        public DetailDTO(Detail detail, Integer memberCount, List<SocialMemberList> socialMemberList) {
            this.detail = detail;
            this.memberCount = memberCount;
            this.socialMemberList = socialMemberList;
        }

        @Data
        public static class Detail {
            private Integer id;
            private String name;
            private String image;
            private String info;
            private List<CategoryDTO> categories;
            private String createdAt;

            public Detail(Social social) {
                this.id = social.getId();
                this.name = social.getName();
                this.image = social.getImage();
                this.info = social.getInfo();
                this.categories = social.getCategory().stream().map(CategoryDTO::new).collect(Collectors.toList());
                this.createdAt = LocalDateTimeFormatter.convert(social.getCreatedAt());
            }

            @Data
            public static class CategoryDTO {
                private Integer id;
                private String name;

                public CategoryDTO(Category category) {
                    this.id = category.getId();
                    this.name = category.getCategoryNameId().getName();
                }
            }
        }

        // 소셜 멤버 리스트 DTO
        @Data
        public static class SocialMemberList {
            private Integer id;
            private Integer userId;
            private String nickname;
            private String email;
            private LocalDate birth;
            private SocialMemberRoleEnum role;

            public SocialMemberList(SocialMember socialMember) {
                this.id = socialMember.getId();
                this.userId = socialMember.getUserId().getId();
                this.nickname = socialMember.getUserId().getNickname();
                this.email = socialMember.getUserId().getEmail();
                this.birth = socialMember.getUserId().getBirth();
                this.role = socialMember.getRole();
            }
        }
    }
}

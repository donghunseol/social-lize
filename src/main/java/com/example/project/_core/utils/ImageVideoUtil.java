package com.example.project._core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageVideoUtil {

    // 파일 업로드 결과를 저장하는 클래스, 파일 이름과 파일 경로를 포함합니다.
    public static class FileUploadResult {
        private String fileName; // 업로드된 파일의 이름
        private String filePath; // 파일이 저장된 경로

        // 파일 이름과 경로를 초기화하는 생성자
        public FileUploadResult(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath.substring(1); // 경로의 첫 문자를 제거합니다.
        }

        // fileName의 getter 메서드
        public String getFileName() {
            return fileName;
        }

        // filePath의 getter 메서드
        public String getFilePath() {
            return filePath;
        }
    }

    // 이미지 또는 영상 파일인지 확인하는 메서드
    private static boolean isImageOrVideoFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null &&
                (contentType.startsWith("image/") || contentType.startsWith("video/"));
    }

    // 하나의 파일을 업로드하는 메서드
    public static FileUploadResult uploadFile(MultipartFile file) {
        // 파일이 이미지 또는 영상이 아닌 경우 예외를 던짐
        if (!isImageOrVideoFile(file)) {
            throw new IllegalArgumentException("이미지 또는 영상 파일만 업로드할 수 있습니다.");
        }

        // UUID를 사용하여 고유한 파일 이름 생성
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        // 지정된 디렉토리에 파일 경로 생성
        Path filePath = Paths.get("./upload", fileName);

        try {
            // 업로드 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(filePath.getParent());
            // 지정된 경로에 파일 작성
            Files.write(filePath, file.getBytes());
        } catch (Exception e) {
            // 오류가 발생하면 스택 트레이스를 출력하고 런타임 예외를 던짐
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류 발생: " + e.getMessage());
        }

        // 파일 업로드 결과를 반환
        return new FileUploadResult(fileName, filePath.toString());
    }

    // 여러 파일을 업로드하는 메서드
    public static List<FileUploadResult> uploadFiles(List<MultipartFile> files) {
        // 각 파일 업로드 결과를 저장할 리스트
        List<FileUploadResult> uploadResults = new ArrayList<>();
        // 각 파일을 순회하면서 업로드
        for (MultipartFile file : files) {
            uploadResults.add(uploadFile(file));
        }
        // 업로드 결과 리스트를 반환
        return uploadResults;
    }
}

package com.example.project._core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class HlsUtil {

    // 원본 비디오 파일 경로를 생성하는 메서드
    public static String getRawVideoPath(String videoFileName) {
        return Paths.get("upload", videoFileName).toAbsolutePath().toString();
    }

    // 변환된 HLS 비디오 파일 경로를 생성하는 메서드
    public static String getConvertVideoPath(String outputFileName) {
        return Paths.get("src", "main", "resources", "convert", outputFileName).toAbsolutePath().toString();
    }

    // HLS 변환된 비디오 리소스를 반환하는 메서드
    public static Resource getHlsResource(String fileName) {
        try {
            File file = new File(getConvertVideoPath(fileName + ".m3u8"));
            return new FileSystemResource(file.getCanonicalPath());
        } catch (Exception e) {
            throw new RuntimeException("HLS 리소스를 가져오는 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // HLS로 비디오 파일을 변환하는 메서드
    public static void convertHls(String fileName) {
        try {
            // 변환할 원본 비디오 파일 경로
            String inputFilePath = getRawVideoPath(fileName);

            // 변환된 HLS 플레이리스트 파일 경로
            String outputFilePath = getConvertVideoPath(fileName);

            // 출력 디렉토리 생성
            new File(outputFilePath).getParentFile().mkdirs();

            // ffmpeg 명령어 생성
            String[] command = {
                    "ffmpeg",
                    "-i", inputFilePath,
                    "-profile:v", "baseline",
                    "-level", "3.0",
                    "-start_number", "0",
                    "-hls_time", "10",
                    "-hls_list_size", "0",
                    "-f", "hls",
                    outputFilePath
            };

            // 프로세스 빌더 생성 및 실행
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // ffmpeg 출력을 읽어들이기 위한 BufferedReader 사용
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // ffmpeg 출력을 콘솔에 출력
                }
            }

            int exitCode = process.waitFor(); // 프로세스 종료 코드 확인
            if (exitCode != 0) {
                throw new RuntimeException("ffmpeg 실행 중 오류 발생. 종료 코드: " + exitCode);
            }

            System.out.println("HLS 변환 완료");
        } catch (Exception e) {
            throw new RuntimeException("HLS 변환 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
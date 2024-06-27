package com.example.project._core.utils;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class HlsUtil {

    // HLS 변환된 비디오 리소스를 반환하는 메서드
    public static Resource getHlsResource(String fileName) {
        try {
            File file = new File(getConvertVideoPath(fileName + ".m3u8"));
            return new FileSystemResource(file.getCanonicalPath());
        } catch (Exception e) {
            throw new RuntimeException("HLS 리소스를 가져오는 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // 원본 비디오 파일 경로를 생성하는 메서드
    public static String getRawVideoPath(String videoFileName) {
        return "./upload/" + videoFileName;
    }

    // 변환된 HLS 비디오 파일 경로를 생성하는 메서드
    public static String getConvertVideoPath(String outputFileName) {
        return "/src/main/resources/convert/" + outputFileName;
    }

    // HLS로 비디오 파일을 변환하는 메서드
    public static void convertHls(String fileName) {
        try {
            // 변환할 원본 비디오 파일 경로
            String videoFilePath = getRawVideoPath(fileName);

            // 변환된 HLS 플레이리스트 파일 경로
            String convertPath = getConvertVideoPath(fileName);

            // FFmpegBuilder를 사용하여 변환 작업 설정
            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(videoFilePath)  // 입력 파일 설정
                    .addOutput(convertPath)  // 출력 파일 설정
                    .addExtraArgs("-profile:v", "baseline")  // 비디오 프로파일 설정
                    .addExtraArgs("-level", "3.0")  // H.264 레벨 설정
                    .addExtraArgs("-start_number", "0")  // 세그먼트 시작 번호 설정
                    .addExtraArgs("-hls_time", "10")  // 세그먼트 길이 설정
                    .addExtraArgs("-hls_list_size", "0")  // m3u8 파일에 포함될 세그먼트 수 설정
                    .addExtraArgs("-f", "hls")  // 출력 포맷 설정 (HLS)
                    .done();  // 설정 완료

            // FFmpegExecutor를 사용하여 실행
            FFmpegExecutor executor = new FFmpegExecutor(new FFmpeg(), new FFprobe());
            executor.createJob(builder).run();

            System.out.println("HLS 변환 완료: " + convertPath); // 변환이 완료되면 로그 출력

        } catch (Exception e) {
            throw new RuntimeException("HLS 변환 중 오류 발생: " + e.getMessage(), e);
        }
    }
}

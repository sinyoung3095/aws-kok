package com.example.kok.controller;

import com.example.kok.service.ConsoleExperienceFileService;
import com.example.kok.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files/experience")
public class ConsoleExperienceFileDownloadController {
    private final ConsoleExperienceFileService fileService;
    private final S3Service s3Service;

    @GetMapping("/download/{experienceNoticeId}/{memberId}")
    public ResponseEntity<String> downloadFile(@PathVariable Long experienceNoticeId, @PathVariable Long memberId) {

        var file = fileService.getDownloadUrl(memberId, experienceNoticeId)
                .orElseThrow(() -> new RuntimeException("파일 정보를 찾을 수 없습니다."));

        log.info("memberId={}, experienceNoticeId={}, filePath={}, fileName={}",
                memberId, experienceNoticeId, file.getFilePath(), file.getFileOriginName());

        String downloadUrl = s3Service.getPreSignedDownloadUrl(
                file.getFilePath(),
                file.getFileOriginName(),
                Duration.ofMinutes(5)
        );

        return ResponseEntity.ok(downloadUrl);
    }
}

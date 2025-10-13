package com.example.kok.controller;

import com.example.kok.service.ResumeFileService;
import com.example.kok.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/**")
public class ResumeFileDownloadController {
    private final ResumeFileService fileService;
    private final S3Service s3Service;

    @GetMapping("/download/{experienceNoticeId}/{memberId}")
    public ResponseEntity<String> downloadFile(@PathVariable Long memberId, @PathVariable Long experienceNoticeId) {
        var file = fileService.getFile(memberId, experienceNoticeId)
                .orElseThrow(() -> new RuntimeException("파일 정보를 찾을 수 없습니다."));

        String url = s3Service.getPreSignedDownloadUrl(
                file.getFilePath(),
                file.getFileName(),
                Duration.ofMinutes(5)
        );

        return ResponseEntity.ok(url);
    }
}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.service.CommunityReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
@Slf4j
@RequiredArgsConstructor
public class CommunityReportController {
    private final CommunityReportService communityReportService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> reportPost(@PathVariable Long postId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails == null) {
            return ResponseEntity.ok(false);
        }

        Long memberId = customUserDetails.getId();
        boolean existReported = communityReportService.reportPost(postId, memberId);

        if (existReported) {
            return ResponseEntity.badRequest().body("이미 신고한 게시글입니다.");
        }
        return ResponseEntity.ok("신고가 접수되었습니다.");
    }
}

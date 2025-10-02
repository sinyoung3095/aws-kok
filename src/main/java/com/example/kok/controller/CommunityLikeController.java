package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostLikeDTO;
import com.example.kok.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class CommunityLikeController {
    private final CommunityLikeService communityLikeService;

//    게시글 좋아요
    @PostMapping
    public ResponseEntity<?> postLike(@RequestBody PostLikeDTO postLikeDTO) {
        postLikeDTO.setMemberId(5L);
        communityLikeService.postLike(postLikeDTO);
        return ResponseEntity.ok(postLikeDTO);

    }

//    게시글 좋아요 취소
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> removePostLike(@PathVariable Long postId) {
        Long memberId = 5L;
        communityLikeService.removePostLike(postId, memberId);
        return ResponseEntity.ok().build();
    }

    // 추천 갯수 조회
    @GetMapping("/{postId}/count")
    public ResponseEntity<?> getPostLikeCount(@PathVariable Long postId) {
        return ResponseEntity.ok(communityLikeService.getPostLikeCount(postId));
    }

    // 추천 여부 확인
    @GetMapping("/{postId}/check")
    public ResponseEntity<?> checkedPostLike(@PathVariable Long postId) {
        Long memberId = 5L;
        return ResponseEntity.ok(communityLikeService.checkedPostLike(postId, memberId));
    }
}

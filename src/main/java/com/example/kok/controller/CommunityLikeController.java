package com.example.kok.controller;

import com.example.kok.dto.PostLikeDTO;
import com.example.kok.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class CommunityLikeController {
    private final CommunityLikeService communityLikeService;

//    게시글 좋아요
    @PostMapping
    public ResponseEntity<?> postLike(@RequestBody PostLikeDTO postLikeDTO) {
        postLikeDTO.setMemberId(1L);
        communityLikeService.postLike(postLikeDTO);

        int likesCount = communityLikeService.getPostLikeCount(postLikeDTO.getPostId());
        boolean liked = communityLikeService.checkedPostLike(postLikeDTO.getPostId(), postLikeDTO.getMemberId());

        return ResponseEntity.ok(Map.of(
                "postId", postLikeDTO.getPostId(),
                "likesCount", likesCount,
                "liked", liked
        ));
    }

//    게시글 좋아요 취소
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> removePostLike(@PathVariable Long postId) {
        Long memberId = 1L;
        communityLikeService.removePostLike(postId, memberId);

        int likesCount = communityLikeService.getPostLikeCount(postId);
        boolean liked = communityLikeService.checkedPostLike(postId, memberId);

        return ResponseEntity.ok(Map.of(
                "postId", postId,
                "likesCount", likesCount,
                "liked", liked
        ));
    }

    // 추천 갯수 조회
    @GetMapping("/{postId}/count")
    public ResponseEntity<?> getPostLikeCount(@PathVariable Long postId) {
        return ResponseEntity.ok(communityLikeService.getPostLikeCount(postId));
    }

    // 추천 여부 확인
    @GetMapping("/{postId}/check")
    public ResponseEntity<?> checkedPostLike(@PathVariable Long postId) {
        Long memberId = 1L;
        boolean liked = communityLikeService.checkedPostLike(postId, memberId);
        return ResponseEntity.ok(liked);
    }
}

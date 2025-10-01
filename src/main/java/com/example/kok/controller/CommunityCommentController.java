package com.example.kok.controller;

import com.example.kok.dto.CommentDTO;
import com.example.kok.service.CommunityCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommunityCommentController {
    private final CommunityCommentService communityCommentService;

//    댓글 작성
    @PostMapping
    public ResponseEntity<?> write(@RequestBody CommentDTO commentDTO) {
        // 임시 memberId 설정
        commentDTO.setMemberId(3L);
        communityCommentService.write(commentDTO);
        return ResponseEntity.ok(commentDTO);
    }

//    댓글 목록
    @GetMapping("/{postId}")
    public ResponseEntity<?> list(@PathVariable Long postId) {
        List<CommentDTO> comments = communityCommentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }
}

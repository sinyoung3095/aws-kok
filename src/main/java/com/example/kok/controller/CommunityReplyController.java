package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ReplyDTO;
import com.example.kok.service.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Slf4j
public class CommunityReplyController {
    private final CommunityReplyService communityReplyService;

//    대댓글 작성
    @PostMapping
    public ResponseEntity<?> write(@RequestBody ReplyDTO replyDTO,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        replyDTO.setMemberId(customUserDetails.getId());
        communityReplyService.write(replyDTO);
        return ResponseEntity.ok(replyDTO);
    }

//    대댓글 목록
    @GetMapping("/{commentId}")
    public ResponseEntity<?> list(@PathVariable Long commentId) {
        List<ReplyDTO> replies = communityReplyService.getReplies(commentId);
        return ResponseEntity.ok(replies);
    }
}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Reply", description = "대댓글 API")
public interface CommunityReplyControllerDocs {

//    대댓글 작성
    @Operation(summary = "게시글 상세", description = "게시글 ID를 이용해 상세 내용을 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 ID")
            }
    )
    public ResponseEntity<?> write(@RequestBody ReplyDTO replyDTO,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    대댓글 목록
    @Operation(summary = "게시글 상세", description = "게시글 ID를 이용해 상세 내용을 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 ID")
            }
    )
    public ResponseEntity<?> list(@PathVariable Long commentId,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    대댓글 수정
    @Operation(summary = "게시글 상세", description = "게시글 ID를 이용해 상세 내용을 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 ID")
            }
    )
    public ResponseEntity<?> updateReply(@PathVariable("replyId") Long replyId,
                                         @RequestBody ReplyDTO replyDTO,
                                         @AuthenticationPrincipal CustomUserDetails user);

//    대댓글 삭제
    @Operation(summary = "게시글 상세", description = "게시글 ID를 이용해 상세 내용을 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 ID")
            }
    )
    public ResponseEntity<?> deleteReply(@PathVariable("replyId") Long replyId);
}

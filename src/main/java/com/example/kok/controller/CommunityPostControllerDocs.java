package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostsCriteriaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "CommunityPost", description = "CommunityPost API")
public interface CommunityPostControllerDocs {
    @Operation(summary = "게시글 목록", description = "게시글 목록 불러오기",
                parameters = {
                    @Parameter(name = "page", description = "화면에 불러 올 게시글 목록 페이지(무한 스크롤)")
                }
    )
    public ResponseEntity<PostsCriteriaDTO> getPosts(@PathVariable("page") int page,
                                                     @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "게시글 상세", description = "게시글 상세 불러오기",
            parameters = {
                    @Parameter(name = "id", description = "화면에 불러 올 게시글 id")
            }
    )
    public ResponseEntity<?> getOne(@PathVariable("id") Long id,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails);
}

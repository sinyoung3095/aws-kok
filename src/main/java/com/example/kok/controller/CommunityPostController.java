package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import com.example.kok.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@Slf4j
@RequiredArgsConstructor
public class CommunityPostController {
    private final CommunityPostService communityPostService;

//    게시글 목록 조회
    @GetMapping("/{page}")
    public PostsCriteriaDTO getPosts(@PathVariable("page") int page) {
        return communityPostService.getList(page);
    }

//    게시글 조회
//    @GetMapping("/post/{id}")
//    public ResponseEntity<PostDTO> getOne(@PathVariable("id") Long id) {
//        PostDTO postDTO = communityPostService.getPost(id);
//        return ResponseEntity.ok(postDTO);
//    }

//    게시글 작성
//    @PostMapping
//    public ResponseEntity<?> write(@RequestParam("postContent") String postContent,
//                                   @RequestParam(value="files", required=false) List<MultipartFile> files,
//                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setPostContent(postContent);
//        postDTO.setMemberId(customUserDetails.getId());
//
//        communityPostService.write(postDTO, files != null ? files : List.of());
//        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO.getId());
//    }

//    임시 작성
    @PostMapping
    public ResponseEntity<?> write(@RequestParam("postContent") String postContent,
                                   @RequestParam(value="files", required=false) List<MultipartFile> files) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostContent(postContent);
        postDTO.setMemberId(1L); // 임시회원 아이디 설정
        communityPostService.write(postDTO, files != null ? files : List.of());
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO.getId());
    }

//    게시글 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable("id") Long id,
//                                    @RequestParam("postContent") String postContent,
//                                    @RequestParam(value="deleteFiles", required=false) Long[] deleteFiles,
//                                    @RequestParam(value="files", required=false) List<MultipartFile> files,
//                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setId(id);
//        postDTO.setPostContent(postContent);
//        postDTO.setMemberId(customUserDetails.getId());
//
//        PostDTO updatedPost = communityPostService.update(postDTO, deleteFiles, files != null ? files : List.of());
//        return ResponseEntity.ok(updatedPost);
//    }

//    게시글 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
//        communityPostService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}

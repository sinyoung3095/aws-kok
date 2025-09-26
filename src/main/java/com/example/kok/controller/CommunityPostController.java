package com.example.kok.controller;

import com.example.kok.dto.PostsCriteriaDTO;
import com.example.kok.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityPostController {
    private final CommunityPostService communityPostService;

    @GetMapping("/{page}")
    public ResponseEntity<?> list(@PathVariable("page") int page) {
        PostsCriteriaDTO postCriteriaDTO = communityPostService.getList(page);
        if (postCriteriaDTO == null || postCriteriaDTO.getPosts().isEmpty()) {
            return ResponseEntity.ok(postCriteriaDTO);
        }
        return ResponseEntity.ok(postCriteriaDTO);
    }
}

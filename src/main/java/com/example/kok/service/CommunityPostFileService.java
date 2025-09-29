package com.example.kok.service;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;

import java.util.List;
import java.util.Optional;

public interface CommunityPostFileService {
    // 파일 저장
    void saveFile(FileDTO fileDTO);

    // 게시글 파일 매핑 저장
    void savePostFile(PostFileDTO postFileDTO);

    // 게시글 ID로 파일 목록 조회
    List<PostFileDTO> getFilesByPostId(Long postId);

    // 파일 경로 조회
    Optional<PostFileDTO> getFilePathByFileId(Long fileId);

    // 단일 파일 조회
    Optional<PostFileDTO> getPostFile(Long id);

    // 게시글 파일 삭제
    void deleteFile(Long id);
}

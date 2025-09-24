package com.example.kok.service;

import com.example.kok.dto.PostCriteriaDTO;

public interface PostService {
//    게시글 전체 조회
    public PostCriteriaDTO getList(int page);
}

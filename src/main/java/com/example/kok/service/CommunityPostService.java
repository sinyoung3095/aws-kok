package com.example.kok.service;

import com.example.kok.dto.PostCriteriaDTO;

public interface CommunityPostService {
//    게시글 전체 조회
    public PostCriteriaDTO getList(int page);
}

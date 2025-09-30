package com.example.kok.service;

import com.example.kok.dto.CommentDTO;
import com.example.kok.dto.CommentsCriteriaDTO;

public interface CommunityCommentService {

//    댓글 작성
    public void write(CommentDTO commentDTO);

//    게시글 내 댓글 목록
    public CommentsCriteriaDTO getComments(Long postId, int page);
}

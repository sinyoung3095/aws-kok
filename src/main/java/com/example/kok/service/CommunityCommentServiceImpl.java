package com.example.kok.service;

import com.example.kok.dto.CommentDTO;
import com.example.kok.dto.CommentsCriteriaDTO;
import com.example.kok.repository.CommunityCommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService {
    private final CommunityCommentDAO communityCommentDAO;

//    댓글 작성
    @Override
    public void write(CommentDTO commentDTO) {
        communityCommentDAO.save(commentDTO);
        
    }

//    게시글 내 댓글 목록
    @Override
    public CommentsCriteriaDTO getComments(Long postId, int page) {
        return null;
    }
}

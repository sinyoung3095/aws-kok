package com.example.kok.mapper;

import com.example.kok.dto.CommentDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityCommentMapper {

//    댓글 작성
    public void insertComment(CommentDTO commentDTO);

//    게시글 내 댓글 목록
    public List<CommentDTO> selectCommentsByPostId(@Param("postId") Long postId, @Param("criteria") Criteria criteria);
}

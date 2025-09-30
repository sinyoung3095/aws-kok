package com.example.kok.mapper;

import com.example.kok.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityReplyMapper {

//    대댓글 작성
    public void insertReply(ReplyDTO replyDTO);

//    대댓글 수정
    public void updateReply(ReplyDTO replyDTO);

//    대댓글 삭제
    public void deleteReply(Long id);
}

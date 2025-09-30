package com.example.kok.service;

import com.example.kok.dto.ReplyDTO;

public interface CommunityReplyService {

//    대댓글 작성
    public void write(ReplyDTO replyDTO);

//    대댓글 수정
    public void update(ReplyDTO replyDTO);

//    대댓글 삭제
    public void delete(Long id);
}

package com.example.kok.repository;

import com.example.kok.dto.ReplyDTO;
import com.example.kok.mapper.CommunityReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommunityReplyDAO {
    private final CommunityReplyMapper communityReplyMapper;

//    대댓글 작성
    public void save(ReplyDTO replyDTO) {
        communityReplyMapper.insertReply(replyDTO);
    }

//    대댓글 수정
    public void update(ReplyDTO replyDTO) {
        communityReplyMapper.updateReply(replyDTO);
    }

//    대댓글 삭제
    public void delete(Long id) {
        communityReplyMapper.deleteReply(id);
    }
}

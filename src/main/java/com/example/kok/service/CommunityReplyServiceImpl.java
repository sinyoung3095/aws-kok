package com.example.kok.service;

import com.example.kok.dto.ReplyDTO;
import com.example.kok.repository.CommunityReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityReplyServiceImpl implements CommunityReplyService {
    private final CommunityReplyDAO communityReplyDAO;

//    대댓글 작성
    @Override
    public void write(ReplyDTO replyDTO) {
        communityReplyDAO.save(replyDTO);
    }

//    대댓글 수정
    @Override
    public void update(ReplyDTO replyDTO) {
        communityReplyDAO.update(replyDTO);
    }

//    대댓글 삭제
    @Override
    public void delete(Long id) {
        communityReplyDAO.delete(id);
    }
}

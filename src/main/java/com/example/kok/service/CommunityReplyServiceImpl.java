package com.example.kok.service;

import com.example.kok.dto.ReplyDTO;
import com.example.kok.repository.CommunityReplyDAO;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    댓글 내 대댓글 목록
    @Override
    public List<ReplyDTO> getReplies(Long commentId, Long memberId) {
        List<ReplyDTO> replies = communityReplyDAO.findAll(commentId);

        replies.forEach(reply -> {
            reply.setRelativeDate(DateUtils.toRelativeTime(reply.getCreatedDateTime()));
            reply.setOwner(memberId != null && memberId.equals(reply.getMemberId()));
        });

        return replies;
    }

//    대댓글 갯수
    @Override
    public int getRepliesCount(Long commentId) {
        return communityReplyDAO.countByCommentId(commentId);
    }
}

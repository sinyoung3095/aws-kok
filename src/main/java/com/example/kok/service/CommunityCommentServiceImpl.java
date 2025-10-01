package com.example.kok.service;

import com.example.kok.dto.CommentDTO;
import com.example.kok.dto.CommentsCriteriaDTO;
import com.example.kok.dto.ReplyDTO;
import com.example.kok.repository.CommunityCommentDAO;
import com.example.kok.repository.CommunityReplyDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService {
    private final CommunityCommentDAO communityCommentDAO;
    private final CommunityReplyDAO communityReplyDAO;

//    댓글 작성
    @Override
    public void write(CommentDTO commentDTO) {
        communityCommentDAO.save(commentDTO);
        
    }

//    댓글 수정
    @Override
    public void update(CommentDTO commentDTO) {
        communityCommentDAO.update(commentDTO);
    }

//    댓글 삭제
    @Override
    public void delete(Long id) {
        communityCommentDAO.delete(id);
    }

//    게시글 내 댓글 목록
    @Override
    public List<CommentDTO> getComments(Long postId) {
        List<CommentDTO> comments = communityCommentDAO.findAll(postId);

        comments.forEach(comment -> {
            comment.setRelativeDate(DateUtils.toRelativeTime(comment.getCreatedDateTime()));

            List<ReplyDTO> replies = communityReplyDAO.findAll(comment.getId());
            replies.forEach(reply -> {
                reply.setRelativeDate(DateUtils.toRelativeTime(reply.getCreatedDateTime()));
            });

            comment.setReplies(replies);
            comment.setTotalReplyCount(replies.size());
        });

        return comments;
    }

    @Override
    public int commentsCountByPostId(Long postId) {
        int commentCount = communityCommentDAO.getTotal(postId);
        List<CommentDTO> comments = communityCommentDAO.findAll(postId);

        int replyCount = 0;
        for(CommentDTO commentDTO : comments) {
            replyCount += communityReplyDAO.countByCommentId(commentDTO.getId());
        }
        return commentCount + replyCount;
    }
}

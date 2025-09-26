package com.example.kok.service;

import com.example.kok.dto.PostCriteriaDTO;
import com.example.kok.dto.PostDTO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class PostServiceImpl implements PostService {
//    @Override
//    public PostCriteriaDTO getList(int page) {
//        PostCriteriaDTO postCriteriaDTO = new PostCriteriaDTO();
//        Criteria criteria = new Criteria(page, postDAO.findCountAll());
//        List<PostDTO> posts = postDAO.findAll(criteria);
//        posts.forEach((post) -> {
//            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDatetime()));
//        });
//
//        criteria.setHasMore(posts.size() > criteria.getRowCount());
//
////        11개 가져왔으면, 마지막 1개 삭제
//        if(criteria.isHasMore()){
//            posts.remove(posts.size() - 1);
//        }
//
//        postCriteriaDTO.setPosts(posts);
//        postCriteriaDTO.setCriteria(criteria);
//        return postCriteriaDTO;
//    }
//}

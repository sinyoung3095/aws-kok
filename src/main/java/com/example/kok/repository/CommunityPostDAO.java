package com.example.kok.repository;

import com.example.kok.domain.PostVO;
import com.example.kok.dto.PostDTO;
import com.example.kok.mapper.CommunityPostMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommunityPostDAO {
    private final CommunityPostMapper communityPostMapper;
    //    목록
    public List<PostDTO> findAll(Criteria criteria){
        return communityPostMapper.selectCommunityPosts(criteria);
    }

    //    전체 개수
    public int findCountAll() {
        return communityPostMapper.selectCommunityPostCount();
    }

    //    조회
    public Optional<PostDTO> findById(Long id) {
        return communityPostMapper.selectCommunityPost(id);
    }

    //    추가
    public void save(PostDTO postDTO) {
        communityPostMapper.insert(postDTO);
    }

    //    삭제
    public void delete(Long id) {
        communityPostMapper.delete(id);
    }

    //    수정
    public void update(PostVO postVO) {
        communityPostMapper.update(postVO);
    }
}

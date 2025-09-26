package com.example.kok.repository;

import com.example.kok.dto.PostFileDTO;
import com.example.kok.mapper.CommunityPostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommunityPostFileDAO {
    private final CommunityPostFileMapper communityPostFileMapper;

//    추가
    public void save(PostFileDTO postFileDTO){
        communityPostFileMapper.insertPostFile(postFileDTO);
    }

//    조회
    public List<PostFileDTO> findAllByPostId(Long postId){
        return communityPostFileMapper.selectPostFilesByPostId(postId);
    }
    public Optional<PostFileDTO> findPostFilePathByPostFileId(Long postFileId){
        return communityPostFileMapper.selectPostFilePathByPostFileId(postFileId);
    }
    public Optional<PostFileDTO> findById(Long id){
        return communityPostFileMapper.selectPostFileById(id);
    }

//    삭제
    public void deleteById(Long id){
        communityPostFileMapper.deletePostFile(id);
    }
}

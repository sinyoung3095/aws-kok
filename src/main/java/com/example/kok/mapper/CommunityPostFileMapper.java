package com.example.kok.mapper;

import com.example.kok.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityPostFileMapper {
//    추가
    public void insertPostFile(PostFileDTO postFileDTO);

//    조회
    public List<PostFileDTO> selectPostFilesByPostId(Long postId);
    public Optional<PostFileDTO> selectPostFilePathByPostFileId(Long postFileId);
    public Optional<PostFileDTO> selectPostFileById(Long id);

//    삭제
    public void deletePostFile(Long id);

}

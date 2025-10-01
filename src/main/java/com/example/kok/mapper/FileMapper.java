package com.example.kok.mapper;

import com.example.kok.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface FileMapper {
//    기업 아이디로 프사 조회
    public Optional<FileDTO> selectFileByCompanyId(Long userId);
//    파일 작성
    public void insertFile(FileDTO fileDTO);
}

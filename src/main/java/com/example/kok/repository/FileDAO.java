package com.example.kok.repository;

import com.example.kok.dto.FileDTO;
import com.example.kok.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    기업 id로 프사 조회
    public Optional<FileDTO> findFileByCompanyId(Long userId) {
        return fileMapper.selectFileByCompanyId(userId);
    }
}

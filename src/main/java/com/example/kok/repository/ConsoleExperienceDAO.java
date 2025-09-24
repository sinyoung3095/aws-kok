package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.mapper.ConsoleExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceDAO {
    private final ConsoleExperienceMapper consoleExperienceMapper;

//    공고 전체 조회
    public List<ConsoleExperienceNoticeDTO> findAll() {
        return consoleExperienceMapper.selectProductAll();
    }
}

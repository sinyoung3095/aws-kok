package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestInternDTO;
import com.example.kok.mapper.RequestExperienceMapper;
import com.example.kok.mapper.RequestInternMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestInternDAO {
    private final RequestInternMapper requestInternMapper;

//    회원별 인턴 지원서 조회
    public List<RequestInternDTO> selectAllInternById(Long id){
        return requestInternMapper.selectRequestInternById(id);
    }
}

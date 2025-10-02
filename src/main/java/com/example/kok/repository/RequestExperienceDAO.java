package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.mapper.RequestExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestExperienceDAO {
    private final RequestExperienceMapper requestExperienceMapper;

//    지원서 추가
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO){
        requestExperienceMapper.insertRequest(requestExperienceDTO);
    }

//    회원별 지원서 조회
    public List<RequestExperienceDTO> selectAllRequestById(Long id){
        return requestExperienceMapper.selectRequestById(id);
    }
}

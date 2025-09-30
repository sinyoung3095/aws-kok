package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.mapper.RequestExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RequestExperienceDAO {
    private final RequestExperienceMapper requestExperienceMapper;

//    지원서 추가
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO){
        requestExperienceMapper.insertRequest(requestExperienceDTO);
    }
}

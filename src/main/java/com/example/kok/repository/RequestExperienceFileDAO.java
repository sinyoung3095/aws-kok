package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceFileDTO;
import com.example.kok.mapper.RequestExperienceFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RequestExperienceFileDAO {
    private final RequestExperienceFileMapper requestExperienceFileMapper;

    public void saveRequestFile(RequestExperienceFileDTO requestExperienceFileDTO){
        requestExperienceFileMapper.insertRequestFile(requestExperienceFileDTO);
    }
}

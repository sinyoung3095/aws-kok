package com.example.kok.mapper;

import com.example.kok.dto.RequestExperienceDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestExperienceMapper {
//    지원서 넣기
    public void insertRequest(RequestExperienceDTO requestExperienceDTO);
}

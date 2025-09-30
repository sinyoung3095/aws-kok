package com.example.kok.mapper;

import com.example.kok.dto.RequestExperienceFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestExperienceFileMapper {
//    지원서 파일 insert
    public void insertRequestFile(RequestExperienceFileDTO requestExperienceFileDTO);
}

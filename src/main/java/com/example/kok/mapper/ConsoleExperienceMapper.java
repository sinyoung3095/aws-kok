package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleExperienceMapper {

//    공고 전체 조회
//    public List<ConsoleExperienceNoticeDTO> selectProductAll(Criteria criteria);
    public List<ConsoleExperienceNoticeDTO> selectProductAll();

}

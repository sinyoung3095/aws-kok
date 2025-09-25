package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceNoticeCriteriaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleExperienceMapper {

//    공고 전체 조회
    public List<ConsoleExperienceNoticeCriteriaDTO> selectExperienceAll(@Param("criteria") com.example.youeatieat.util.Criteria criteria);

//    공고 개수 조회(전체)
    public int selectCountAll();

}

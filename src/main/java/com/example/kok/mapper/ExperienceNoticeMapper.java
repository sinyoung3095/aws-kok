package com.example.kok.mapper;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperienceNoticeMapper {
//    전체 목록 조회
    public List<ExperienceNoticeDTO> selectAllExperienceNotice(Criteria criteria);

//    전체 개수 조회
    public int selectCountAll();
}

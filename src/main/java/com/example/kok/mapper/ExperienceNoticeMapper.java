package com.example.kok.mapper;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperienceNoticeMapper {
//    전체 목록 조회
    public List<ExperienceNoticeDTO> selectAllExperienceNotice(@Param("criteria")Criteria criteria, @Param("search")Search search);

//    전체 개수 조회
    public int selectCountAll();

//    단일 조회
    public ExperienceNoticeDTO selectById(Long id);

//    직군 조회
    public String selectJobNameByExpId(Long id);

//    최신 체험 공고 4개 조회
    public List<ExperienceNoticeDTO> selectLatestFour();

//    기업별 체험 공고 조회
    public List<ExperienceNoticeDTO> selectListById(Long userId);
}

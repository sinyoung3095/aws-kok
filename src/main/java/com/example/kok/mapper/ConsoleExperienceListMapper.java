package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConsoleExperienceListMapper {

//    공고 목록
    public List<ConsoleExperienceListDTO> selectExperienceByCompany(@Param("companyId") Long companyId,
                                                                    @Param("criteria") Criteria criteria,
                                                                    @Param("status") Status status,
                                                                    @Param("keyword") String keyword);

//    공고 개수(전체, 모집중)
    public int selectCountByCompany(@Param("companyId") Long companyId,
                                    @Param("status") Status status,
                                    @Param("keyword") String keyword);

//    공고 상태 변경
    public void updateListStatus(@Param("noticeId") Long noticeId,
                                 @Param("status") Status status);

//    지원자개수(활성화 공고 지원자, 누적 지원자)
    public int selectRequestCountByCompany(@Param("companyId") Long companyId,
                                           @Param("active") boolean active);

}

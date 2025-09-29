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
                                                                    @Param("criteria") Criteria criteria);
//    공고 개수
    public int selectCountByCompany(Long companyId);

//    모집중인 공고 개수
    public int selectActiveCountByCompany(Long companyId);

    public void updateListStatus(@Param("noticeId") Long noticeId,
                          @Param("status") Status status);

//    활성화된 공고의 지원자, 누적 지원자
    public ConsoleExperienceListCriteriaDTO selectRequestStatsByCompany(Long companyId);

}

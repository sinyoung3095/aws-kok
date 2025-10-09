package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConsoleExperienceApplicationMapper {

//    지원자 상세 내용
    ConsoleExperienceApplicantDTO selectApplicationDetail(
            @Param("memberId") Long memberId,
            @Param("experienceNoticeId") Long experienceNoticeId
    );

}

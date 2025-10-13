package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ConsoleExperienceApplicationMapper {

//    지원자 상세 조회
    ConsoleExperienceApplicantDTO selectApplicationDetail(
            @Param("memberId") Long memberId,
            @Param("experienceNoticeId") Long experienceNoticeId
    );

    Optional<FileDTO> selectResumeFileByMemberId(
            @Param("memberId") Long memberId,
            @Param("experienceNoticeId") Long experienceNoticeId);


}

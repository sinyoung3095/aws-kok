package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConsoleInternApplicationMapper {

//    지원자 상세 조회
    ConsoleInternApplicantDTO selectApplicationDetail(
            @Param("memberId") Long memberId,
            @Param("internNoticeId") Long internNoticeId
    );

    Optional<FileDTO> selectResumeFileByMemberId(
            @Param("memberId") Long memberId,
            @Param("internNoticeId") Long internNoticeId);

//    공고별 지원자 목록
    List<ConsoleInternApplicantDTO> selectApplicantsByNoticeId(@Param("internNoticeId") Long internNoticeId);

}

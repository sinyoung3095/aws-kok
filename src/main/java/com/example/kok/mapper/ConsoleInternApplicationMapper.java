package com.example.kok.mapper;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConsoleInternApplicationMapper {

//    지원자 상세 내용
    ConsoleInternApplicantDTO selectApplicationDetail(
            @Param("memberId") Long memberId,
            @Param("internNoticeId") Long internNoticeId
    );

}

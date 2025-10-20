package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;

import java.util.List;

public interface ConsoleExperienceApplicationService {
//    지원자 상세 내용
    ConsoleExperienceApplicantDTO getApplicantDetail(Long memberId, Long experienceNoticeId);

//    지원자 목록 (공고 ID로 조회)
    List<ConsoleExperienceApplicantDTO> getApplicantsByNoticeId(Long experienceNoticeId);

//    여러 명의 지원자 상세 조회
    List<ConsoleExperienceApplicantDTO> getApplicantsDetailsByMemberIds(Long experienceNoticeId, List<Long> memberIdList);

//    평가하기 가능 여부 조회
    public boolean isEvalOk(Long experienceNoticeId, Long memberId);
}

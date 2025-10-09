package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;

public interface ConsoleExperienceApplicationService {
//    지원자 상세 내용
    ConsoleExperienceApplicantDTO getApplicantDetail(Long memberId, Long experienceNoticeId);

}

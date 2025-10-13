package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;

public interface ConsoleInternApplicationService {
//    지원자 상세 내용
    ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId);

}

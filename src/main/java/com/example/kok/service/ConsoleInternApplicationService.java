package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;

import java.util.List;

public interface ConsoleInternApplicationService {
//    지원자 상세 내용
    ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId);

//    지원자 목록 (공고 ID로 조회)
    List<ConsoleInternApplicantDTO> getApplicantsByNoticeId(Long internNoticeId);
}
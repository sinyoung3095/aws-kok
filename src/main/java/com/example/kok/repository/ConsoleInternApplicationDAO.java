package com.example.kok.repository;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.mapper.ConsoleInternApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConsoleInternApplicationDAO {
    private final ConsoleInternApplicationMapper consoleInternMapper;

//    지원자 상세 내용
    public ConsoleInternApplicantDTO findApplicantDetail(Long memberId, Long experienceNoticeId) {
        return consoleInternMapper.selectApplicationDetail(memberId, experienceNoticeId);
    }

}

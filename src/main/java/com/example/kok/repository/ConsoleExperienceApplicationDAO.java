package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.mapper.ConsoleExperienceApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceApplicationDAO {
    private final ConsoleExperienceApplicationMapper consoleExperienceMapper;

//    지원자 상세 내용
    public ConsoleExperienceApplicantDTO findApplicantDetail(Long memberId, Long experienceNoticeId) {
        return consoleExperienceMapper.selectApplicationDetail(memberId, experienceNoticeId);
    }

    public Optional<FileDTO> findResumeFileByMemberId(Long memberId, Long experienceNoticeId) {
        return consoleExperienceMapper.selectResumeFileByMemberId(memberId, experienceNoticeId);
    }

}

package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleExperienceApplicationMapper;
import com.example.kok.mapper.ConsoleExperienceDetailMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceApplicationDAO {
    private final ConsoleExperienceApplicationMapper consoleExperienceMapper;

//    지원자 상세 내용
    public ConsoleExperienceApplicantDTO findApplicantDetail(Long memberId, Long experienceNoticeId) {
        return consoleExperienceMapper.selectApplicationDetail(memberId, experienceNoticeId);
    }

}

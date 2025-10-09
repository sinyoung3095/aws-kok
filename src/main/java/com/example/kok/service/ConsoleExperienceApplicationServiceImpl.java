package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleExperienceApplicationDAO;
import com.example.kok.repository.ConsoleExperienceDetailDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleExperienceApplicationServiceImpl implements ConsoleExperienceApplicationService {
    private final ConsoleExperienceApplicationDAO consoleExperienceApplicationDAO;

    @Override
    public ConsoleExperienceApplicantDTO getApplicantDetail(Long memberId, Long experienceNoticeId) {
        return consoleExperienceApplicationDAO.findApplicantDetail(memberId, experienceNoticeId);
    }

}

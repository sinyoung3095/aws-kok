package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.repository.ConsoleExperienceApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsoleExperienceApplicationServiceImpl implements ConsoleExperienceApplicationService {
    private final ConsoleExperienceApplicationDAO consoleExperienceApplicationDAO;

    @Override
    public ConsoleExperienceApplicantDTO getApplicantDetail(Long memberId, Long experienceNoticeId) {
        ConsoleExperienceApplicantDTO applicantDetail =
                consoleExperienceApplicationDAO.findApplicantDetail(memberId, experienceNoticeId);

        // 파일 정보 조회 (Optional)
        Optional<FileDTO> fileInfo = consoleExperienceApplicationDAO.findResumeFileByMemberId(memberId, experienceNoticeId);

        // 파일 있으면 세팅
        fileInfo.ifPresent(file -> {
            applicantDetail.setFilePath(file.getFilePath());
            applicantDetail.setFileName(file.getFileOriginName());
        });

        return applicantDetail;
    }

}

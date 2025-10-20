package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.repository.ConsoleExperienceApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public List<ConsoleExperienceApplicantDTO> getApplicantsByNoticeId(Long experienceNoticeId) {
        return consoleExperienceApplicationDAO.findApplicantsByNoticeId(experienceNoticeId);
    }

//    여러 명의 지원자 상세 조회
    @Override
    public List<ConsoleExperienceApplicantDTO> getApplicantsDetailsByMemberIds(Long experienceNoticeId, List<Long> memberIdList) {
        List<ConsoleExperienceApplicantDTO> results = new ArrayList<>();

        for (Long memberId : memberIdList) {
            // 기존 단일 조회 재활용
            ConsoleExperienceApplicantDTO applicantDetail =
                    consoleExperienceApplicationDAO.findApplicantDetail(memberId, experienceNoticeId);

            // 파일 정보 조회
            Optional<FileDTO> fileInfo =
                    consoleExperienceApplicationDAO.findResumeFileByMemberId(memberId, experienceNoticeId);

            // 파일 있으면 DTO에 세팅
            fileInfo.ifPresent(file -> {
                applicantDetail.setFilePath(file.getFilePath());
                applicantDetail.setFileName(file.getFileOriginName());
            });

            results.add(applicantDetail);
        }

        return results;
    }

//    평가하기 가능 여부 조회
    public boolean isEvalOk(Long experienceNoticeId, Long memberId) {
        RequestExperienceDTO exp=consoleExperienceApplicationDAO.findEvalOk(experienceNoticeId, memberId);
        LocalDate now = LocalDate.now();
        if(exp.getRequestExperienceStatus().equals("accept")&&exp.getExperienceEndDate().isBefore(now)){
            return true;
        } else{
            return false;
        }
    }

}

package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.repository.ConsoleInternApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsoleInternApplicationServiceImpl implements ConsoleInternApplicationService {
    private final ConsoleInternApplicationDAO consoleInternApplicationDAO;

    @Override
    public ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId) {
        ConsoleInternApplicantDTO applicantDetail =
                consoleInternApplicationDAO.findApplicantDetail(memberId, internNoticeId);

        // 파일 정보 조회 (Optional)
        Optional<FileDTO> fileInfo = consoleInternApplicationDAO.findResumeFileByMemberId(memberId, internNoticeId);

        // 파일 있으면 세팅
        fileInfo.ifPresent(file -> {
            applicantDetail.setFilePath(file.getFilePath());
            applicantDetail.setFileName(file.getFileOriginName());
        });

        return applicantDetail;
    }

    @Override
    public List<ConsoleInternApplicantDTO> getApplicantsByNoticeId(Long internNoticeId) {
        return consoleInternApplicationDAO.findApplicantsByNoticeId(internNoticeId);
    }

}

package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.RequestDownloadUrlDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleInternApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsoleInternApplicationServiceImpl implements ConsoleInternApplicationService {
    private final ConsoleInternApplicationDAO consoleInternApplicationDAO;
    private final S3Service s3Service;

    @Override
    public ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId) {
        return consoleInternApplicationDAO.findApplicantDetail(memberId, internNoticeId);

    }

//    목록
    @Override
    public List<ConsoleInternApplicantDTO> getApplicantsByNoticeId(Long internNoticeId) {
        return consoleInternApplicationDAO.findApplicantsByNoticeId(internNoticeId);
    }

//    여러 명의 지원자 상세 조회
    @Override
    public List<ConsoleInternApplicantDTO> getApplicationsDetailsByMemberIds(Long experienceNoticeId, List<Long> memberIdList) {
        List<ConsoleInternApplicantDTO> results = new ArrayList<>();

        for (Long memberId : memberIdList) {
            // 기존 단일 조회 재활용
            ConsoleInternApplicantDTO applicantDetail =
                    consoleInternApplicationDAO.findApplicantDetail(memberId, experienceNoticeId);

            // 파일 정보 조회
            Optional<FileDTO> fileInfo =
                    consoleInternApplicationDAO.findResumeFileByMemberId(memberId, experienceNoticeId);

            // 파일 있으면 DTO에 세팅
            fileInfo.ifPresent(file -> {
                applicantDetail.setFilePath(file.getFilePath());
                applicantDetail.setFileName(file.getFileOriginName());
            });

            results.add(applicantDetail);
        }

        return results;
    }

//    지원자 상태 변경
    @Override
    public void updateApplicantStatus(Long userId, Long internNoticeId, RequestStatus requestInternStatus) {
        consoleInternApplicationDAO.updateApplicantStatus(userId, internNoticeId, requestInternStatus);
    }

    @Override
    public RequestDownloadUrlDTO getApplicationFileInfo(List<Long> memberIdList, Long experienceNoticeId) {
        RequestDownloadUrlDTO requestDownloadUrlDTO = new RequestDownloadUrlDTO();
        List<String> downloadUrls = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        getApplicationsDetailsByMemberIds(experienceNoticeId, memberIdList).forEach(applicantDetail -> {
            if (applicantDetail.getFilePath() != null && applicantDetail.getFilePath() != null) {
                String downloadUrl = s3Service.getPreSignedDownloadUrl(
                        applicantDetail.getFilePath(),
                        applicantDetail.getFileName(),
                        Duration.ofMinutes(5)
                );
                downloadUrls.add(downloadUrl);
                fileNames.add(applicantDetail.getFileName());
            }
        });

        requestDownloadUrlDTO.setUrls(downloadUrls);
        requestDownloadUrlDTO.setFileNames(fileNames);
        return requestDownloadUrlDTO;
    }

}

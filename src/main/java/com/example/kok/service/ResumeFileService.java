package com.example.kok.service;

import com.example.kok.dto.FileDTO;
import com.example.kok.repository.ConsoleExperienceApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeFileService {
    private final ConsoleExperienceApplicationDAO resumeFileDAO;

//    조회
    public Optional<FileDTO> getFile(Long memberId, Long experienceNoticeId) {
        return resumeFileDAO.findResumeFileByMemberId(memberId, experienceNoticeId);
    }

}

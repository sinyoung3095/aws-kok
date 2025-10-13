package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.repository.ConsoleInternApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsoleInternApplicationServiceImpl implements ConsoleInternApplicationService {
    private final ConsoleInternApplicationDAO consoleInternApplicationDAO;

    @Override
    public ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId) {
        return consoleInternApplicationDAO.findApplicantDetail(memberId, internNoticeId);
    }

}

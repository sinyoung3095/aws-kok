package com.example.kok.repository;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.ConsoleInternListDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleInternDetailMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleInternDetailDAO {
    private final ConsoleInternDetailMapper consoleInternMapper;

//    공고 상세 내용
    public ConsoleInternListDTO findInternDetailByCompany(Long internNoticeId) {
        return consoleInternMapper.selectInternDatailByCompany(internNoticeId);
    }

//    지원 목록
    public List<ConsoleInternApplicantDTO> findAllByCompany(Long companyId, RequestStatus status, Criteria criteria) {
        String statusStr = (status != null) ? status.name().toLowerCase() : null;

        return consoleInternMapper.selectInternApplicantByCompany(companyId, statusStr, criteria);
    }

//    지원 목록 개수
    public int findCountByCompany(Long companyId, RequestStatus status) {
        String statusStr = (status != null) ? status.name().toLowerCase() : null;

        return consoleInternMapper.selectInternApplicantCountByCompany(companyId, statusStr);
    }

}

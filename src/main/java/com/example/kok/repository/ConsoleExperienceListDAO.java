package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.ConsoleExperienceListMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceListDAO {
    private final ConsoleExperienceListMapper consoleExperienceMapper;

//    공고 목록
    public List<ConsoleExperienceListDTO> findAllByCompany(Long companyId, Criteria criteria) {
        return consoleExperienceMapper.selectExperienceByCompany(companyId, criteria);
    }

//    공고 개수
    public int findCountByCompany(Long companyId) {
        return consoleExperienceMapper.selectCountByCompany(companyId);
    }

//    모집중인 공고 개수
    public int findActiveByCompany(Long companyId) {
        return consoleExperienceMapper.selectActiveCountByCompany(companyId);
    }

    public void updateListStatus(Long noticeId, Status status) {
        consoleExperienceMapper.updateListStatus(noticeId, status);
    }

//    활성화된 공고의 지원자, 누적 지원자
    public ConsoleExperienceListCriteriaDTO getRequestStats(Long companyId) {
        return consoleExperienceMapper.selectRequestStatsByCompany(companyId);
    }

}

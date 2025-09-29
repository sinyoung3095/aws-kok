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

//    공고 목록(전체)
    public List<ConsoleExperienceListDTO> findAllByCompany(Long companyId, Criteria criteria, Status status, String keyword) {
        return consoleExperienceMapper.selectExperienceByCompany(companyId, criteria, status, keyword);
    }

//    공고 개수
    public int findCountByCompany(Long companyId, Status status, String keyword) {
        return consoleExperienceMapper.selectCountByCompany(companyId, status, keyword);
    }

//    모집중인 공고 개수
    public int findActiveCountByCompany(Long companyId, Status status, String keyword) {
        return consoleExperienceMapper.selectCountByCompany(companyId, status, keyword);
    }

//    공고 상태 변경
    public void updateListStatus(Long noticeId, Status status) {
        consoleExperienceMapper.updateListStatus(noticeId, status);
    }

//    활성화된 공고의 지원자
    public int findRequestCountByCompany(Long companyId) {
        return consoleExperienceMapper.selectRequestCountByCompany(companyId, true);
    }

//    누적 지원자
    public int findRequestActiveCountByCompany(Long companyId) {
        return consoleExperienceMapper.selectRequestCountByCompany(companyId, false);
    }

}

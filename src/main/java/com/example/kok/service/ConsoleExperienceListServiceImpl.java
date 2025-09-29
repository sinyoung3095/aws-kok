package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.repository.ConsoleExperienceListDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsoleExperienceListServiceImpl implements ConsoleExperienceListService {
    private final ConsoleExperienceListDAO consoleExperienceDAO;

    @Override
    public ConsoleExperienceListCriteriaDTO getList(Long companyId, int page) {
        ConsoleExperienceListCriteriaDTO consoleExperienceNoticeCriteriaDTO = new ConsoleExperienceListCriteriaDTO();

        int totalCount = consoleExperienceDAO.findCountByCompany(companyId);
        int activeCount = consoleExperienceDAO.findActiveByCompany(companyId);
        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleExperienceListDTO> notices = consoleExperienceDAO.findAllByCompany(companyId, criteria);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleExperienceNoticeCriteriaDTO.setExperienceLists(notices);
        consoleExperienceNoticeCriteriaDTO.setCriteria(criteria);
        consoleExperienceNoticeCriteriaDTO.setTotalCount(totalCount);
        consoleExperienceNoticeCriteriaDTO.setActiveTotalCount(activeCount);

        ConsoleExperienceListCriteriaDTO stats = consoleExperienceDAO.getRequestStats(companyId);
        if (stats != null) {
            consoleExperienceNoticeCriteriaDTO.setTotalRequestCount(stats.getTotalRequestCount());
            consoleExperienceNoticeCriteriaDTO.setActiveRequestCount(stats.getActiveRequestCount());
        }

        return consoleExperienceNoticeCriteriaDTO;
    }

    @Override
    public void updateListStatus(Long noticeId, Status status) {
        consoleExperienceDAO.updateListStatus(noticeId, status);
    }

}

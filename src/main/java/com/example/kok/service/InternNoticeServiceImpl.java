package com.example.kok.service;

import com.example.kok.dto.CompanyInternNoticeCriteriaDTO;
import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.repository.InternNoticeDAO;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternNoticeServiceImpl implements InternNoticeService {

    private final InternNoticeDAO internNoticeDAO;

//    기업별 인턴 공고 목록
    @Override
    public CompanyInternNoticeCriteriaDTO getInternNoticesByCompanyId(int page, Long companyId, Search search) {
        int total = internNoticeDAO.findCountByCompanyId(companyId, search);
        CompanyNoticeCriteria criteria = new CompanyNoticeCriteria(page, total);

        List<InternNoticeDTO> notices = internNoticeDAO.findAllByCompanyId(companyId, criteria, search);

        criteria.setHasMore(criteria.getPage() < criteria.getRealEnd());

        CompanyInternNoticeCriteriaDTO companyInternNoticeCriteriaDTO = new CompanyInternNoticeCriteriaDTO();
        companyInternNoticeCriteriaDTO.setCriteria(criteria);
        companyInternNoticeCriteriaDTO.setInterns(notices);
        return companyInternNoticeCriteriaDTO;
    }
}

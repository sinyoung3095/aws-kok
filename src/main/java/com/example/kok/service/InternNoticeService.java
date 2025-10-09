package com.example.kok.service;

import com.example.kok.dto.CompanyInternNoticeCriteriaDTO;
import com.example.kok.util.Search;

public interface InternNoticeService {

//    기업별 인턴 공고 목록
    public CompanyInternNoticeCriteriaDTO getInternNoticesByCompanyId(int page, Long companyId, Search search);
}

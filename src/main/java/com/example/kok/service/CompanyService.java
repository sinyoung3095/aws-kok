package com.example.kok.service;

import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.CompanyAllNoticeCriteriaDTO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.util.CompanySearch;
import com.example.kok.util.Search;

import java.util.List;

public interface CompanyService {

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId);

//    기업 목록
    public CompaniesCriteriaDTO getCompanyList(int page, CompanySearch search, Long userId);

//    인기 기업 목록
    public List<CompanyDTO> getCompaniesByFollowerCount();
}

package com.example.kok.service;

import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.util.CompanySearch;

import java.util.List;

public interface CompanyService {

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId);

//    기업 목록
    public CompaniesCriteriaDTO getCompanyList(int page, CompanySearch search);

//    인기 기업 목록
    public List<CompanyDTO> getCompaniesByFollowerCount();
}

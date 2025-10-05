package com.example.kok.service;

import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.CompanyDTO;

public interface CompanyService {

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId);

//    기업 목록
    public CompaniesCriteriaDTO getCompanyList(int page);
}

package com.example.kok.service;

import com.example.kok.dto.CompanyDTO;

public interface CompanyService {

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId);
}

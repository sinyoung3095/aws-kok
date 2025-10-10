package com.example.kok.service;

import com.example.kok.dto.AdminCompanyCriteriaDTO;
import com.example.kok.dto.AdminCompanyDTO;
import com.example.kok.dto.AdminMemberCriteriaDTO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.util.Criteria;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId);

//    기업 목록 조회
    public AdminCompanyCriteriaDTO findAllCompanies(int page, String keyword);

//    아이디로 기업 조회
    public Optional<AdminCompanyDTO> findCompany(Long userId);
}

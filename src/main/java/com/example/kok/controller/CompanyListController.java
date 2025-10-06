package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import com.example.kok.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@Slf4j
@RequiredArgsConstructor
public class CompanyListController {
    private final CompanyService companyService;

//    기업 목록
    @GetMapping("/{page}")
    public CompaniesCriteriaDTO getCompanyList(@PathVariable("page") int page) {
        return companyService.getCompanyList(page);
    }
}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.CompanyExperienceNoticeCriteriaDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import com.example.kok.service.CompanyService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.util.CompanySearch;
import com.example.kok.util.Search;
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
    private final ExperienceNoticeService experienceNoticeService;

//    기업 목록
    @GetMapping("/{page}")
    public CompaniesCriteriaDTO getCompanyList(@PathVariable("page") int page, CompanySearch search) {
        return companyService.getCompanyList(page, search);
    }

    @GetMapping("/{companyId}/experiences/{page}")
    public CompanyExperienceNoticeCriteriaDTO getCompanyExperiences(@PathVariable("companyId") Long companyId, @PathVariable("page") int page, Search search) {
        log.info("기업별 체험 공고 목록 - companyId: {}, page: {}, search: {}", companyId, page, search);
        return experienceNoticeService.getExperienceNoticesByCompanyId(page, companyId, search);
    }
}

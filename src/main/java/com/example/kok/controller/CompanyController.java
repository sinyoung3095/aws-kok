package com.example.kok.controller;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.service.CompanyService;
import com.example.kok.util.CompanySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

//    기업 목록
    @GetMapping("/list")
    public String goToCompanyList(Model model, CompanySearch search) {
        model.addAttribute("companies", companyService.getCompanyList(1, search).getCompanies());
        return "company/list";
    }

//    기업 상세
    @GetMapping("/detail/{companyId}")
    public String detailPage(@PathVariable Long companyId, Model model) {
        CompanyDTO company = companyService.findCompanyById(companyId);
        model.addAttribute("company", company);
        return "company/detail";
    }


//    기업 상세 임시
    @GetMapping("/detail")
    public String goToCompanyDetail() {
        return "company/detail";
    }
}

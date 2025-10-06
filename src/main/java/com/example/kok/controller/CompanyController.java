package com.example.kok.controller;

import com.example.kok.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

//    기업 목록
    @GetMapping("/list")
    public String goToCompanyList(Model model) {
        model.addAttribute("companies", companyService.getCompanyList(1).getCompanies());
        return "company/list";
    }

//    기업 상세
    @GetMapping("/detail")
    public String goToCompanyDetail() {
        return "company/detail";
    }
}

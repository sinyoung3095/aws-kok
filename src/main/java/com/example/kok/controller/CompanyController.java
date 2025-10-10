package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.service.CompanyService;
import com.example.kok.service.UserService;
import com.example.kok.util.CompanySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final UserService userService;

//    기업 목록
    @GetMapping("/list")
    public String goToCompanyList(Model model, CompanySearch search, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails == null) {
            return "redirect:/member/login";
        }

        Long userId = customUserDetails.getId();
        model.addAttribute("companies", companyService.getCompanyList(1, search, userId).getCompanies());
        model.addAttribute("user", customUserDetails);
        return "company/list";
    }

//    기업 상세
    @GetMapping("/{companyId}")
    public String detailPage(@PathVariable Long companyId, Model model) {
        CompanyDTO company = companyService.findCompanyById(companyId);
        model.addAttribute("company", company);

        List<CompanyDTO> popularCompanies = companyService.getCompaniesByFollowerCount();
        model.addAttribute("popularCompanies", popularCompanies);

        return "company/detail";
    }
}

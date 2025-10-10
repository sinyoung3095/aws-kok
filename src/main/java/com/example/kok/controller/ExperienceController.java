package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/experience/**")
@RequiredArgsConstructor
public class ExperienceController {
    private final ExperienceNoticeService experienceNoticeService;
//    체험 목록으로 이동
    @GetMapping("list")
    public String goToExpList(@RequestParam(defaultValue = "1") int page, Model model,
                              @RequestParam(required = false) String sharedCompanyId,
                              @RequestParam(required = false) String sharedExperienceId,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @ModelAttribute("search") Search search) {
//        System.out.println("컨트롤러 실행해용");
//        System.out.println("keyword = " + search.getKeyword());
        ExperienceNoticeCriteriaDTO dto =experienceNoticeService.selectAllExperienceNotice(page, search);
//        System.out.println(dto);
        model.addAttribute("experienceNoticeCriteria", dto);
        model.addAttribute("userDTO", customUserDetails);
        model.addAttribute("search", search);
        model.addAttribute("sharedCompanyId", sharedCompanyId);
        model.addAttribute("sharedExperienceId", sharedExperienceId);
        return "experience/list";
    }
}

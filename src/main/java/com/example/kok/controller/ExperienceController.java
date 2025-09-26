package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.service.ExperienceNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/experience/**")
@RequiredArgsConstructor
public class ExperienceController {
    private final ExperienceNoticeService experienceNoticeService;
//    체험 목록으로 이동
    @GetMapping("list")
    public String goToExpList(@RequestParam(defaultValue = "1") int page, Model model,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        model.addAttribute("experienceNoticeCriteria", experienceNoticeService.selectAllExperienceNotice(page));
        model.addAttribute("user", customUserDetails);
        return "experience/list";
    }
}

package com.example.kok.controller;

import com.example.kok.dto.ConsoleCompanyProfileDTO;
import com.example.kok.service.ConsoleProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Slf4j
@Controller
@RequestMapping("/enterprise-console")
@RequiredArgsConstructor
public class ConsoleProfileController {
    private final ConsoleProfileService consoleProfileService;

//    기업 콘솔 프로필 관리
    @GetMapping("/profile")
//    public String goToProfile(@RequestParam("userId") Long userId, Model model) {
    public String goToProfile(Model model) {
        Long userId = 1L; // 테스트용
        ConsoleCompanyProfileDTO profile = consoleProfileService.getProfile(userId);
        model.addAttribute("profile", profile);
        return "enterprise-console/console-profile";
    }

//    기업 프로필 수정 처리
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute ConsoleCompanyProfileDTO companyProfileDTO) {
        consoleProfileService.updateProfile(companyProfileDTO);
        return "redirect:/enterprise-console/profile?userId=" + companyProfileDTO.getUserId();
    }

}

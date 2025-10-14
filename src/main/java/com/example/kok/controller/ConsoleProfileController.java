package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ConsoleCompanyProfileDTO;
import com.example.kok.service.ConsoleProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/enterprise-console")
@RequiredArgsConstructor
public class ConsoleProfileController {
    private final ConsoleProfileService consoleProfileService;

//    기업 콘솔 프로필 관리
    @GetMapping("/profile")
//    public String goToProfile(@RequestParam("userId") Long userId, Model model) {
    public String goToProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              Model model) {
        Long companyId = customUserDetails.getId();
        ConsoleCompanyProfileDTO profile = consoleProfileService.getProfile(companyId);
        model.addAttribute("profile", profile);
        return "enterprise-console/console-profile";
    }

//    기업 프로필 수정 처리
    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute ConsoleCompanyProfileDTO companyProfileDTO,
            @RequestParam(value = "profileFile", required = false) MultipartFile profileFile,
            @RequestParam(value = "backgroundFile", required = false) MultipartFile backgroundFile
    ) {
        List<MultipartFile> multipartFiles = new ArrayList<>();

        if (profileFile != null && !profileFile.isEmpty()) {
            multipartFiles.add(profileFile);
        }
        if (backgroundFile != null && !backgroundFile.isEmpty()) {
            multipartFiles.add(backgroundFile);
        }

        consoleProfileService.updateProfile(companyProfileDTO, multipartFiles);
        return "redirect:/enterprise-console/profile?userId=" + companyProfileDTO.getCompanyId();
    }


}

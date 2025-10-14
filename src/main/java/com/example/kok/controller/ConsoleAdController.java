package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.service.ConsoleAdService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/ad")
@RequiredArgsConstructor
public class ConsoleAdController {
    private final ConsoleAdService consoleAdService;

    //    기업 콘솔 광고 목록
    @GetMapping("/list")
    public String goToList(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        Long companyId = customUserDetails.getId();

        model.addAttribute("companyId", companyId);
        return "enterprise-console/console-add-list";
    }

    //    기업 콘솔 광고 등록/수정
    @GetMapping(value = {"/create", "/edit/{id}"})
    public String goToWrite(HttpServletRequest request, @PathVariable(required = false) Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleAdNoticeDTO());
            return "enterprise-console/console-add-upload";
        }
        ConsoleAdNoticeDTO notice = consoleAdService.getNotice(id);

        consoleAdService.setPreSignedUrl(notice);

        Long companyId = customUserDetails.getId();

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        model.addAttribute("companyId", companyId);
        return "enterprise-console/console-add-upload";
    }

    @PostMapping("/create")
    public String registerAdvertisement(
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        adNoticeDTO.setCompanyId(customUserDetails.getId());

        consoleAdService.registerAdvertisement(adNoticeDTO, multipartFiles);
        return "redirect:/enterprise-console/ad/list";
    }


    @PostMapping("/update")
    public String updateAdvertisement(
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles,
            @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {

        adNoticeDTO.setCompanyId(customUserDetails.getId());

        consoleAdService.modifyNotice(adNoticeDTO, multipartFiles);
        return "redirect:/enterprise-console/ad/list";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteAdvertisement(@PathVariable("id") Long id) {
        consoleAdService.deleteAdvertisement(id);
        return "success";
    }

}

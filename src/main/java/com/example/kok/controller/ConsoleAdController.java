package com.example.kok.controller;

import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.service.ConsoleAdService;
import com.example.kok.service.ConsoleInternNoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String goToList() {
//        List<ConsoleAdNoticeDTO> latestFour = consoleAdService.findLatestFour();
//        model.addAttribute("latestFour", latestFour);

        return "enterprise-console/console-add-list";
    }

//    기업 콘솔 광고 등록
//    @GetMapping("/upload")
//    public String goToUpload() {
//        return "enterprise-console/console-add-upload";
//    }

//    기업 콘솔 광고 등록/수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(HttpServletRequest request, Model model, @PathVariable(required = false) Long id) {
        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleAdNoticeDTO());
            return "enterprise-console/console-add-upload";
        }
        ConsoleAdNoticeDTO notice = consoleAdService.getNotice(id);

        consoleAdService.setPreSignedUrl(notice);

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        return "enterprise-console/console-add-upload";
    }

    @PostMapping("/create")
    public String registerAdvertisement(
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles) {

        System.out.println("📂 파일 개수: " + (multipartFiles == null ? "null" : multipartFiles.size()));
        adNoticeDTO.setCompanyId(1L);

        consoleAdService.registerAdvertisement(adNoticeDTO, multipartFiles);
        return "redirect:/enterprise-console/ad/list";
    }


    @PostMapping("/update")
    public String updateAdvertisement(
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles) {

        System.out.println("📂 수정 시 파일 개수: " + (multipartFiles == null ? "null" : multipartFiles.size()));
        adNoticeDTO.setCompanyId(1L);

        consoleAdService.modifyNotice(adNoticeDTO, multipartFiles);
        return "redirect:/enterprise-console/ad/list";
    }




}

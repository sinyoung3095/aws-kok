package com.example.kok.controller;

import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.service.ConsoleExperienceListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/experience")
@RequiredArgsConstructor
public class ConsoleExperienceController {
    private final ConsoleExperienceListService consoleExperienceListService;

//    기업 콘솔 체험 공고 목록
    @GetMapping("/list")
    public String goToList() {
        return "enterprise-console/console-experience-list";
    }

//    기업 콘솔 체험 공고 등록, 수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(HttpServletRequest request, Model model, @PathVariable(required = false) Long id) {
        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleExperienceNoticeRequestDTO());
            return "enterprise-console/console-experience-update";
        }
        ConsoleExperienceNoticeRequestDTO notice = consoleExperienceListService.getNotice(id);

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        return "enterprise-console/console-experience-update";
    }

//    기업 콘솔 체험 지원서 목록 -> 상세(내용 더 보여야함)("/detail/{id}")
    @GetMapping("/applicate-list")
    public String goToApplicateList() {
        return "enterprise-console/console-experience-applicate-list";
    }

//    기업 콘솔 체험 지원서
    @GetMapping("/application")
    public String goToApplication() {
        return "enterprise-console/console-experience-application";
    }

//    기업 콘솔 평가서
    @GetMapping("/review")
    public String goToReview() {
        return "enterprise-console/console-review";
    }



}

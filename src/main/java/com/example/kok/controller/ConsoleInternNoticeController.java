package com.example.kok.controller;

import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.service.ConsoleInternNoticeService;
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
@RequestMapping("/enterprise-console/intern")
@RequiredArgsConstructor
public class ConsoleInternNoticeController {
    private final ConsoleInternNoticeService consoleInternNoticeService;

    //    기업 콘솔 인턴 공고 목록
    @GetMapping("/list")
    public String goToList() {
        return "enterprise-console/console-intern-list";
    }

    //    기업 콘솔 인턴 공고 등록, 수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(HttpServletRequest request, Model model, @PathVariable(required = false) Long id) {
        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleInternNoticeRequestDTO());
            return "enterprise-console/console-intern-update";
        }
        ConsoleInternNoticeRequestDTO notice = consoleInternNoticeService.getNotice(id);

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        return "enterprise-console/console-intern-update";
    }


    //    기업 콘솔 인턴 지원서 목록 -> 상세(내용 더 보여야함)("/detail/{id}")
    @GetMapping("/applicate-list")
    public String goToApplicateList() {
        return "enterprise-console/console-intern-applicate-list";
    }

    //    기업 콘솔 인턴 지원서
    @GetMapping("/application")
    public String goToApplication() {
        return "enterprise-console/console-intern-application";
    }

}

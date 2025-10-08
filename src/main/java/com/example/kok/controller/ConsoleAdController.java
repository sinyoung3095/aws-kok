package com.example.kok.controller;

import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.service.ConsoleAdService;
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
@RequestMapping("/enterprise-console/ad")
@RequiredArgsConstructor
public class ConsoleAdController {
    private final ConsoleAdService consoleAdService;

//    기업 콘솔 광고 목록
    @GetMapping("/list")
    public String goToList() {
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

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        return "enterprise-console/console-add-upload";
    }

}

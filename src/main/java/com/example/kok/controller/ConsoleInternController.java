package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/intern")
@RequiredArgsConstructor
public class ConsoleInternController {

    //    기업 콘솔 인턴 공고 목록
    @GetMapping("/list")
    public String goToList() {
        return "enterprise-console/console-hire-list";
    }

    //    기업 콘솔 인턴 공고 등록
    @GetMapping("/create")
    public String goToCreate() {
        return "enterprise-console/console-hire-update";
    }

    //    기업 콘솔 인턴 공고 수정
    @GetMapping("/edit/{id}")
    public String goToEdit() {
        return "enterprise-console/console-hire-update";
    }

    //    기업 콘솔 인턴 공고 등록
//    @GetMapping("/upload")
//    public String goToUpload() {
//        return "enterprise-console/console-hire-upload";
//    }

    //    기업 콘솔 인턴 지원서 목록
    @GetMapping("/applicate-list")
    public String goToApplicateList() {
        return "enterprise-console/console-hire-applicate-list";
    }

    //    기업 콘솔 인턴 지원서
    @GetMapping("/application")
    public String goToApplication() {
        return "enterprise-console/console-hire-application";
    }

}

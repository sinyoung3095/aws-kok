package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/experience")
@RequiredArgsConstructor
public class ConsoleExperienceController {

//    기업 콘솔 체험 공고 목록
    @GetMapping("/list")
    public String goToList() {
        return "enterprise-console/console-experience-list";
    }

//    기업 콘솔 체험 공고 등록
    @GetMapping("/create")
    public String goToCreate() {
        return "enterprise-console/console-experience-update";
    }

//    기업 콘솔 체험 공고 수정
    @GetMapping("/edit/{id}")
    public String goToEdit() {
        return "enterprise-console/console-experience-update";
    }

//    기업 콘솔 체험 공고 등록
//    @GetMapping("/upload")
//    public String goToUpload() {
//        return "enterprise-console/console-experience-upload";
//    }

//    기업 콘솔 체험 지원서 목록
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

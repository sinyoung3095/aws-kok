package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/ad")
@RequiredArgsConstructor
public class ConsoleAdController {

//    기업 콘솔 광고 목록
    @GetMapping("/list")
    public String goToList() {
        return "enterprise-console/console-add-list";
    }

//    기업 콘솔 광고 등록
    @GetMapping("/upload")
    public String goToUpload() {
        return "enterprise-console/console-add-upload";
    }

}

package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/enterprise-console")
@RequiredArgsConstructor
public class ConsoleProfileController {

//    기업 콘솔 프로필 관리
    @GetMapping("/profile")
    public String goToProfile() {
        return "enterprise-console/console-profile";
    }

}

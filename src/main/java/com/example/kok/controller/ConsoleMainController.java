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
public class ConsoleMainController {

//    기업 콘솔 홈
    @GetMapping("/")
    public String goToHome() {
        return "enterprise-console/console-home";
    }

}

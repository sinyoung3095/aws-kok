package com.example.kok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main-page/**")
public class mainExController {

    @GetMapping("main-ex")
    public String  goToEx(){
        return "main-page/main-ex";
    }
    @GetMapping("login-header")
    public String goToLoginHeader(){
        return "main-page/login-header";
    }
}

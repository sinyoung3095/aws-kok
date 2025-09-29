package com.example.kok.controller;


import com.example.kok.auth.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main-page/**")
public class MainpageController {

    @GetMapping("main-ex")
    public String  goToEx(){
        return "main-page/main-ex";
    }
    @GetMapping("login-header")
    public String goToLoginHeader(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        model.addAttribute("user", customUserDetails);
        return "main-page/login-header";
    }
}

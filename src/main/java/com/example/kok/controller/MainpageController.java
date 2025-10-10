package com.example.kok.controller;


import com.example.kok.auth.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/main-page/**")
public class MainpageController {

    @GetMapping("main-ex")
    public String  goToEx(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        model.addAttribute("userDTO", customUserDetails);
        return "main-page/main-ex";
    }

    @GetMapping("header")
    public String goToHeader(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){

        return "main-page/header";
    }
    @GetMapping("login-header")
    public String goToLoginHeader(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        log.info(customUserDetails.toString());
        model.addAttribute("userDTO", customUserDetails);
        return "main-page/login-header";
    }
    @GetMapping("side-bar")
    public String goToSideBar(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        return "main-page/side-bar";
    }
    @GetMapping("login-side-bar")
    public  String goToLoginSideBar(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        model.addAttribute("userDTO", customUserDetails);
        return "main-page/login-side-bar";
    }
    @GetMapping("404-page")
    public String goTo404Page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        model.addAttribute("userDTO", customUserDetails);
        return "main-page/404-page";
    }


}

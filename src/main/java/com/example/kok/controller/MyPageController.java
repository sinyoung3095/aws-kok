package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/**")
@RequiredArgsConstructor
public class MyPageController {
    @GetMapping("page")
    public String goToMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return "mypage/page";
    }
}

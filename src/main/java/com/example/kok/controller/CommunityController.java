package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/community/**")
@RequiredArgsConstructor
public class CommunityController {

//    커뮤니티
    @GetMapping("page")
    public String goToCommunityPage() {
        return "community/page";
    }
}

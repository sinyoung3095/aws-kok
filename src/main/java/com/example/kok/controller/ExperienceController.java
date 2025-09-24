package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/experience/**")
@RequiredArgsConstructor
public class ExperienceController {
//    체험 목록으로 이동
    @GetMapping("list")
    public String goToExpList(@RequestParam(defaultValue = "1") int page, Model model) {
        return "experience/list";
    }
}

package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/**")
@RequiredArgsConstructor
public class AdminController {

//    advertise
    @GetMapping("advertise")
    public String goToAdvertisePage() {
        return "admin/advertise";
    }
}

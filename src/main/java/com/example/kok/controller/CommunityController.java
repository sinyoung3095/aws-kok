package com.example.kok.controller;

import com.example.kok.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/community/**")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityPostService communityPostService;

//    커뮤니티
    @GetMapping("page")
    public String goToCommunityPage(Model model) {
        model.addAttribute("posts", communityPostService.getList(1).getPosts());
        return "community/page";
    }
}

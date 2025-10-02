package com.example.kok.controller;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.service.CommunityPostService;
import com.example.kok.service.ExperienceNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityPostService communityPostService;
    private final ExperienceNoticeService experienceNoticeService;

//    커뮤니티
    @GetMapping("/page")
    public String goToCommunityPage(Model model,
                                    @AuthenticationPrincipal com.example.kok.auth.CustomUserDetails customUserDetails) {

        Long memberId = (customUserDetails != null) ? customUserDetails.getId() : null;
        model.addAttribute("posts", communityPostService.getList(1, memberId).getPosts());

//        최신 공고 4개 조회
        List<ExperienceNoticeDTO> latestFour = experienceNoticeService.findLatestFour();
        model.addAttribute("latestFour", latestFour);

        return "community/page";
    }

}

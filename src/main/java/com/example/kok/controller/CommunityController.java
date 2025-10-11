package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.service.CommunityPostService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.service.MemberService;
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
    private final MemberService memberService;

    @GetMapping("/page")
    public String goToCommunityPage(Model model,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long memberId = null;
        if (customUserDetails != null) {
            memberId = customUserDetails.getId();
            model.addAttribute("userDTO", customUserDetails);

            memberService.findMembersByMemberId(memberId)
                    .ifPresent(userMemberDTO -> model.addAttribute("member", userMemberDTO));
        }

        model.addAttribute("posts", communityPostService.getList(1, memberId).getPosts());

        List<ExperienceNoticeDTO> latestFour = experienceNoticeService.findLatestFour();
        model.addAttribute("latestFour", latestFour);

        return "community/page";
    }

}

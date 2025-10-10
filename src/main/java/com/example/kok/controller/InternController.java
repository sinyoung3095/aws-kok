package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.InternNoticeCriteriaDTO;
import com.example.kok.service.InternNoticeService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/intern/**")
@RequiredArgsConstructor
public class InternController {
    private final InternNoticeService internNoticeService;
    //    인턴 공고 목록으로 이동
    @GetMapping("list")
    public String goToIntList(@RequestParam(defaultValue = "1") int page, Model model,
                              @RequestParam(required = false) String sharedCompanyId,
                              @RequestParam(required = false) String sharedInternId,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @ModelAttribute("search") Search search) {
        InternNoticeCriteriaDTO dto =internNoticeService.selectAllInternNotice(page, search);
        model.addAttribute("internNoticeCriteria", dto);
        model.addAttribute("userDTO", customUserDetails);
        model.addAttribute("search", search);
        model.addAttribute("sharedCompanyId", sharedCompanyId);
        model.addAttribute("sharedInternId", sharedInternId);
        return "intern/list";
    }
}

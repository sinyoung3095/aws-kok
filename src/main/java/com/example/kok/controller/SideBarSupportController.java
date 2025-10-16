package com.example.kok.controller;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/**")
@RequiredArgsConstructor
public class SideBarSupportController {
    private final AdminService adminService;

    @GetMapping("side-bar/support/{page}")
    public AdminNoticeCriteriaDTO goToSupportSideBar (@PathVariable int page,
                                                      @RequestParam(required = false) String keyword){
        return adminService.supportList(page, keyword);
    }

    @GetMapping("side-bar/support/detail/{id}")
    public AdminNoticeDTO goToSupportSideBarDetail (@PathVariable Long id) {
        return adminService.getNotice(id).orElseThrow(PostNotFoundException::new);
    }
}

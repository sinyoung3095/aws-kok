package com.example.kok.controller;

import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/support/**")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;

//    공지 목록
    @GetMapping("{page}")
    public AdminNoticeCriteriaDTO list(@PathVariable("page") int page){
        log.info("page = {}", page);
        return adminService.getList(page);
    }

//    체험 목록
//    @GetMapping("{page}")
//    public ExperienceNoticeDTO listExperience(@PathVariable("page") int page){
//        log.info("page = {}", page);
//        return
//    }



}

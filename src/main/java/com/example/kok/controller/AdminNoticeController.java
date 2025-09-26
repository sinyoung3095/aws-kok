package com.example.kok.controller;

import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.service.AdminNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/support/**")
@RequiredArgsConstructor
public class AdminNoticeController {
    private final AdminNoticeService adminNoticeService;

//    목록
    @GetMapping("{page}")
    public AdminNoticeCriteriaDTO list(@PathVariable("page") int page){
        log.info("page = {}", page);
        return adminNoticeService.getList(page);
    }
}

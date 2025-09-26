package com.example.kok.controller;

import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.service.AdminNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support/**")
@RequiredArgsConstructor
public class AdminNoticeController {
    private final AdminNoticeService adminNoticeService;

//    목록
    @GetMapping("{page}")
    public ResponseEntity<?> list(@PathVariable("page") int page){
        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = adminNoticeService.getList(page);
        if(adminNoticeCriteriaDTO == null || adminNoticeCriteriaDTO.getNoticeList().size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(adminNoticeCriteriaDTO);
        }
        return ResponseEntity.ok(adminNoticeCriteriaDTO);
    }
}

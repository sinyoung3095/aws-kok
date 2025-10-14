package com.example.kok.controller;

import com.example.kok.dto.*;
import com.example.kok.service.ConsoleAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/ad")
public class ConsoleAdApiController {
    private final ConsoleAdService adService;

    //    목록
    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId, @PathVariable("page") int page, @RequestParam(required = false) String keyword) {
        ConsoleAdNoticeCriteriaDTO adCriteriaDTO = adService.getList(companyId, page, keyword);
        if (adCriteriaDTO == null || adCriteriaDTO.getAdLists().size() == 0) {
            return ResponseEntity.ok(adCriteriaDTO);
        }

        return ResponseEntity.ok(adCriteriaDTO);
    }

}
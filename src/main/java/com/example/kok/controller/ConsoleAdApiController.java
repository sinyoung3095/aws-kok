package com.example.kok.controller;

import com.example.kok.dto.*;
import com.example.kok.enumeration.Status;
import com.example.kok.service.ConsoleAdService;
import com.example.kok.service.ConsoleInternNoticeService;
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
        if(adCriteriaDTO == null || adCriteriaDTO.getAdLists().size() == 0){
            return ResponseEntity.ok(adCriteriaDTO);
        }

        return ResponseEntity.ok(adCriteriaDTO);
    }

//    등록
    @PostMapping("/create")
    public ResponseEntity<?> createNotice(@RequestBody ConsoleAdNoticeDTO adNoticeDTO) {
        adService.registerAdvertisement(adNoticeDTO);
        return ResponseEntity.ok(adNoticeDTO);
    }

//    수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Long id,
                                          @RequestBody ConsoleAdNoticeDTO adNoticeDTO) {
        adNoticeDTO.setId(id);
        adService.modifyNotice(adNoticeDTO);

        log.info("수정 요청 DTO = {}", adNoticeDTO);
        return ResponseEntity.ok(adNoticeDTO);
    }
}

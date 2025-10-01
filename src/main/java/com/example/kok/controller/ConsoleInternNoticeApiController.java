package com.example.kok.controller;

import com.example.kok.dto.ConsoleInternNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.service.ConsoleInternNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/intern")
public class ConsoleInternNoticeApiController {
    private final ConsoleInternNoticeService internService;

//    공고 목록
    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId, @PathVariable("page") int page, @RequestParam(value = "status", required = false) String statusStr, @RequestParam(required = false) String keyword) {
        Status status = null;
        if (statusStr != null && !statusStr.isEmpty()) {
            status = Status.valueOf(statusStr.toUpperCase());
        }

        ConsoleInternNoticeCriteriaDTO internCriteriaDTO = internService.getList(companyId, page, status, keyword);
        if(internCriteriaDTO == null || internCriteriaDTO.getInternLists().size() == 0){
            return ResponseEntity.ok(internCriteriaDTO);
        }

        return ResponseEntity.ok(internCriteriaDTO);
    }

//    공고 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateNoticeStatus(@PathVariable("id") Long id,
                                          @RequestBody ConsoleInternNoticeDTO consoleInternDTO) {
        internService.updateListStatus(id, consoleInternDTO.getInternNoticeStatus());
        return ResponseEntity.ok(consoleInternDTO);
    }

//    공고 등록
    @PostMapping("/create")
    public ResponseEntity<?> createNotice(@RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        internService.registerNotice(noticeRequestDTO);
        return ResponseEntity.ok(noticeRequestDTO);
    }

//    공고 수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Long id,
                                          @RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        noticeRequestDTO.setId(id);
        internService.modifyNotice(noticeRequestDTO);

        log.info("수정 요청 DTO = {}", noticeRequestDTO);
        return ResponseEntity.ok(noticeRequestDTO);
    }

//    @GetMapping("/edit/{id}")
//    public ResponseEntity<ConsoleInternNoticeRequestDTO> getNoticeForEdit(@PathVariable Long id) {
//        return ResponseEntity.ok(internService.getNotice(id));
//    }
}

package com.example.kok.controller;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.service.ConsoleExperienceListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/experience")
public class ConsoleExperienceListApiController {
    private final ConsoleExperienceListService experienceService;

//    공고 목록
    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId, @PathVariable("page") int page, @RequestParam(value = "status", required = false) String statusStr, @RequestParam(required = false) String keyword) {
        Status status = null;
        if (statusStr != null && !statusStr.isEmpty()) {
            status = Status.valueOf(statusStr.toUpperCase());
        }

        ConsoleExperienceListCriteriaDTO experienceCriteriaDTO = experienceService.getList(companyId, page, status, keyword);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getExperienceLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }

        return ResponseEntity.ok(experienceCriteriaDTO);
    }

//    공고 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateNoticeStatus(@PathVariable("id") Long id,
                                          @RequestBody ConsoleExperienceListDTO consoleExperienceDTO) {
        experienceService.updateListStatus(id, consoleExperienceDTO.getExperienceNoticeStatus());
        return ResponseEntity.ok(consoleExperienceDTO);
    }

//    공고 등록
    @PostMapping("/create")
    public ResponseEntity<?> createNotice(@RequestBody ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        experienceService.registerNotice(noticeRequestDTO);
        return ResponseEntity.ok(noticeRequestDTO);
    }

//    공고 수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Long id,
                                          @RequestBody ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        noticeRequestDTO.setId(id);
        experienceService.modifyNotice(noticeRequestDTO);

        log.info("수정 요청 DTO = {}", noticeRequestDTO);
        return ResponseEntity.ok(noticeRequestDTO);
    }

//    @GetMapping("/edit/{id}")
//    public ResponseEntity<ConsoleExperienceNoticeRequestDTO> getNoticeForEdit(@PathVariable Long id) {
//        return ResponseEntity.ok(experienceService.getNotice(id));
//    }
}

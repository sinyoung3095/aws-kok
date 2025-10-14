package com.example.kok.controller;

import com.example.kok.dto.ConsoleExperienceApplicantCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.dto.ConsoleExperienceListRequestDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.service.ConsoleExperienceDetailService;
import com.example.kok.service.ConsoleExperienceListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/experience")
public class ConsoleExperienceApiController {
    private final ConsoleExperienceListService experienceService;
    private final ConsoleExperienceDetailService experienceDetailService;

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
    public ResponseEntity<?> createNotice(@RequestBody ConsoleExperienceListRequestDTO noticeRequestDTO) {
        experienceService.registerNotice(noticeRequestDTO);

        return ResponseEntity.ok(noticeRequestDTO);
    }

//    공고 수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Long id,
                                          @RequestBody ConsoleExperienceListRequestDTO noticeRequestDTO) {
        noticeRequestDTO.setId(id);
        experienceService.modifyNotice(noticeRequestDTO);

        return ResponseEntity.ok(noticeRequestDTO);
    }

//    공고 상세 - 지원자
    @GetMapping("/applicate-list/{experienceNoticeId}/{page}")
    public ResponseEntity<?> applicateList(@PathVariable("experienceNoticeId") Long experienceNoticeId, @PathVariable("page") int page, @RequestParam(value = "status", required = false) RequestStatus status) {

        ConsoleExperienceApplicantCriteriaDTO experienceCriteriaDTO = experienceDetailService.getApplicateList(experienceNoticeId, page, status);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getApplicantLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }

        return ResponseEntity.ok(experienceCriteriaDTO);
    }

//    삭제 추가
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.ok("success");
    }

}

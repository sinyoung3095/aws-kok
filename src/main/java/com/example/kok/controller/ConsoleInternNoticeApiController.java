package com.example.kok.controller;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.service.ConsoleInternApplicationService;
import com.example.kok.service.ConsoleInternDetailService;
import com.example.kok.service.ConsoleInternNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/intern")
public class ConsoleInternNoticeApiController {
    private final ConsoleInternNoticeService internService;
    private final ConsoleInternDetailService internDetailService;
    private final ConsoleInternApplicationService consoleInternApplicationService;


//    공고 목록
    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId,
                                  @PathVariable("page") int page,
                                  @RequestParam(value = "status", required = false) Status status,
                                  @RequestParam(required = false) String keyword) {

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

        return ResponseEntity.ok(Map.of("redirectUrl", "/enterprise-console/intern/list"));
//        return ResponseEntity.ok(noticeRequestDTO);
    }

//    공고 수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Long id,
                                          @RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        noticeRequestDTO.setId(id);
        internService.modifyNotice(noticeRequestDTO);

//        return ResponseEntity.ok(Map.of("redirectUrl", "/enterprise-console/intern/list"));
        return ResponseEntity.ok(noticeRequestDTO);
    }

//    공고 상세 - 지원자
    @GetMapping("/applicate-list/{internNoticeId}/{page}")
    public ResponseEntity<?> applicateList(@PathVariable("internNoticeId") Long internNoticeId, @PathVariable("page") int page, @RequestParam(value = "status", required = false) RequestStatus status) {

        ConsoleInternApplicantCriteriaDTO internCriteriaDTO = internDetailService.getApplicateList(internNoticeId, page, status);
        if(internCriteriaDTO == null || internCriteriaDTO.getApplicantLists().size() == 0){
            return ResponseEntity.ok(internCriteriaDTO);
        }

        return ResponseEntity.ok(internCriteriaDTO);
    }

//    삭제 추가
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
        internService.deleteIntern(id);
        return ResponseEntity.ok("");
    }

//    지원자 상태 변경
    @PutMapping("/applicant/{id}/status")
    public ResponseEntity<?> updateApplicantStatus(
            @PathVariable("id") Long userId,
            @RequestBody ConsoleInternApplicantDTO applicantDTO) {

        log.info("[지원자 상태 변경 요청] userId: {}, status: {}, requestInternStatus: {}",
                userId, applicantDTO.getRequestInternStatus(), applicantDTO.getInternNoticeId());

        consoleInternApplicationService.updateApplicantStatus(userId, applicantDTO.getInternNoticeId(), applicantDTO.getRequestInternStatus());
        return ResponseEntity.ok("지원자 상태가 변경되었습니다.");
    }

}

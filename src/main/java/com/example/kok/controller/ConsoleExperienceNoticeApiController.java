package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.RequestExperienceFileMapper;
import com.example.kok.service.ConsoleExperienceApplicationService;
import com.example.kok.service.ConsoleExperienceDetailService;
import com.example.kok.service.ConsoleExperienceNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.kok.service.S3Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/experience")
public class ConsoleExperienceNoticeApiController {
    private final ConsoleExperienceNoticeService experienceService;
    private final ConsoleExperienceDetailService experienceDetailService;
    private final ConsoleExperienceApplicationService consoleExperienceApplicationService;
    private final RequestExperienceFileMapper requestExperienceFileMapper;
    private final S3Service s3Service;


//    공고 목록
    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId,
                                  @PathVariable("page") int page,
                                  @RequestParam(value = "status", required = false) Status status,
                                  @RequestParam(required = false) String keyword,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  Model model) {

        ConsoleExperienceNoticeCriteriaDTO experienceCriteriaDTO = experienceService.getList(companyId, page, status, keyword);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getExperienceLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }

        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return ResponseEntity.ok(experienceCriteriaDTO);
    }

//    공고 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateNoticeStatus(@PathVariable("id") Long id,
                                          @RequestBody ConsoleExperienceNoticeDTO consoleExperienceDTO) {
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

        return ResponseEntity.ok(noticeRequestDTO);

    }

//    공고 상세 - 지원자
    @GetMapping("/applicate-list/{experienceNoticeId}/{page}")
    public ResponseEntity<?> applicateList(@PathVariable("experienceNoticeId") Long experienceNoticeId,
                                           @PathVariable("page") int page,
                                           @RequestParam(value = "status", required = false) RequestStatus status,
                                           @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                           Model model) {

        ConsoleExperienceApplicantCriteriaDTO experienceCriteriaDTO = experienceDetailService.getApplicateList(experienceNoticeId, page, status);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getApplicantLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }

        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return ResponseEntity.ok(experienceCriteriaDTO);
    }

//    삭제 추가
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.ok("success");
    }

//    여러 명의 지원자 상세 조회
    @PostMapping("/{experienceNoticeId}/applicants/files")
    public ResponseEntity<RequestExperienceDownloadUrlDTO> getApplicantsFileUrls(
            @PathVariable Long experienceNoticeId,
            @RequestBody List<Long> memberIdList) {

        RequestExperienceDownloadUrlDTO requestExperienceDownloadUrlDTO = new RequestExperienceDownloadUrlDTO();

        List<String> downloadUrls = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        for (Long memberId : memberIdList) {
            ConsoleExperienceApplicantDTO file = consoleExperienceApplicationService.getApplicantDetail(memberId, experienceNoticeId);
            String fileName = requestExperienceFileMapper.selectFileNameByMemberId(memberId);

            if (file.getFilePath() != null && file.getFileName() != null) {
                String downloadUrl = s3Service.getPreSignedDownloadUrl(
                        file.getFilePath(),
                        file.getFileName(),
                        Duration.ofMinutes(5)
                );
                downloadUrls.add(downloadUrl);
            }

            fileNames.add(fileName);
        }

        requestExperienceDownloadUrlDTO.setUrls(downloadUrls);
        requestExperienceDownloadUrlDTO.setFileNames(fileNames);

        return ResponseEntity.ok(requestExperienceDownloadUrlDTO);
    }


}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.repository.CompanyProfileFileDAO;
import com.example.kok.service.CompanyService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.service.RequestExperienceService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/experiences/**")
@RequiredArgsConstructor
public class ExperiencesController {
    private final ExperienceNoticeService experienceNoticeService;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final CompanyService companyService;
    private final RequestExperienceService requestExperienceService;

//    목록
    @GetMapping("{page}")
    public ResponseEntity<?> expList(@PathVariable("page") int page, Search search) {
        ExperienceNoticeCriteriaDTO experienceNoticeCriteriaDTO = experienceNoticeService.selectAllExperienceNotice(page, search);
        if(experienceNoticeCriteriaDTO.getExperiences().size()==0||experienceNoticeCriteriaDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(experienceNoticeCriteriaDTO);
        }
        return ResponseEntity.ok(experienceNoticeCriteriaDTO);
    }

//    프로필 사진 url
    @GetMapping("/profile")
    public String profile(Long companyId){
        CompanyProfileFileDTO profile=companyProfileFileDAO.findFileByCompanyId(companyId);
        if(profile==null){
            return "/images/main-page/image.png";
        }
        experienceNoticeService.setPreSignedUrl(profile);
        return profile.getFilePath();
    }

//    상세 불러오기
    @GetMapping("/detail")
    public Map<String,Object> detail(Long companyId, Long experienceId) {
        Map<String,Object> result = new HashMap<>();
        result.put("notice", experienceNoticeService.findNoticeById(experienceId));
        result.put("company", companyService.findCompanyById(companyId));
        return result;
    }

//    간편지원 넣기
    @PostMapping("/request")
    public void requestExperience(Long experienceId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam List<Long> fileIds) {

        RequestExperienceDTO reqDTO = new RequestExperienceDTO();
        reqDTO.setExperienceNoticeId(experienceId);
        reqDTO.setMemberId(customUserDetails.getId());
//        reqDTO.setMemberAlarmSettingId();
        requestExperienceService.applyForExperience(reqDTO, fileIds);
    }

//    공고 저장하기
    @PostMapping("/save")
    public void saveExperience(@RequestParam Long experienceId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        SaveExperienceNoticeDTO saveExp=new SaveExperienceNoticeDTO();
        saveExp.setExperienceNoticeId(experienceId);
        saveExp.setMemberId(customUserDetails.getId());
        experienceNoticeService.saveExp(saveExp);
    }

//    공고 저장 취소하기
    @PostMapping("/unsave")
    public void unsaveExperience(@RequestParam Long experienceId,
             @AuthenticationPrincipal CustomUserDetails customUserDetails){
        SaveExperienceNoticeDTO deleteExp=new SaveExperienceNoticeDTO();
        deleteExp.setExperienceNoticeId(experienceId);
        deleteExp.setMemberId(customUserDetails.getId());
        experienceNoticeService.deleteExp(deleteExp);
    }

}

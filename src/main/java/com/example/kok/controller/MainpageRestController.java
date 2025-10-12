package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main/**")
@RequiredArgsConstructor
@Slf4j
public class MainpageRestController {
    private final FollowDAO  followDAO;
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestInternDAO requestInternDAO;
    private final ExperienceNoticeDAO experienceNoticeDAO;
    private final InternNoticeDAO internNoticeDAO;

    @GetMapping("popular")
    public List<CompanyDTO> findPopularCompany(){
        log.info(followDAO.selectPopularCompany().toString());
        return  followDAO.selectPopularCompany();
    }

    @GetMapping("requestExperience")
    public List<RequestExperienceDTO> findRequestExperience(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestParam(required = false)Long experienceId){

        return requestExperienceDAO.selectAllRequestByUserId(customUserDetails.getId(),experienceId);
    }
    @GetMapping("requestIntern")
    public List<RequestInternDTO> findRequestIntern(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestParam(required = false)Long internId){
        return requestInternDAO.selectAllInternByUserId(customUserDetails.getId(),internId);
    }
    //    체험 목록
    @GetMapping("experience")
    public List<ExperienceNoticeDTO> getExperience(@RequestParam(required = false) String keyword){
        log.info("search = {}", keyword);
        return experienceNoticeDAO.findAllByKeyword(keyword);
    }
    //    체험 목록
    @GetMapping("intern")
    public List<InternNoticeDTO> getIntern(@RequestParam(required = false) String keyword){
        log.info("search = {}", keyword);
        return internNoticeDAO.findAllByKeyword(keyword);
    }
}

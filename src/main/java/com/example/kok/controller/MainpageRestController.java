package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestInternDTO;
import com.example.kok.repository.FollowDAO;
import com.example.kok.repository.RequestExperienceDAO;
import com.example.kok.repository.RequestInternDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/main/**")
@RequiredArgsConstructor
@Slf4j
public class MainpageRestController {
    private final FollowDAO  followDAO;
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestInternDAO requestInternDAO;

    @GetMapping("popular")
    public List<CompanyDTO> findPopularCompany(){
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
}

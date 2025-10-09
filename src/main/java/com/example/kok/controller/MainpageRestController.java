package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.repository.FollowDAO;
import com.example.kok.repository.RequestExperienceDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/main/**")
@RequiredArgsConstructor
public class MainpageRestController {
    private final FollowDAO  followDAO;
    private final RequestExperienceDAO requestExperienceDAO;

    @GetMapping("popular")
    public List<CompanyDTO> findPopularCompany(){
        return  followDAO.selectPopularCompany();
    }

    @GetMapping("requestExperience")
    public List<RequestExperienceDTO> findRequestExperience(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return requestExperienceDAO.selectAllRequestById(customUserDetails.getId());
    }
}

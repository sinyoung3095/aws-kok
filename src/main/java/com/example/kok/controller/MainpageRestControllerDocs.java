package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Tag( name ="mainpageFunction", description = "mainpage Function API")
public interface MainpageRestControllerDocs {
    @Operation
    public List<CompanyDTO> findPopularCompany();
    public List<RequestExperienceDTO> findRequestExperience(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) Long experienceId);
    public List<RequestInternDTO> findRequestIntern(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) Long internId);
    public List<ExperienceNoticeDTO> getExperience(@RequestParam(required = false) String keyword);
    public List<InternNoticeDTO> getIntern(@RequestParam(required = false) String keyword);
    public MemberAlarmSettingDTO findAllByMemberId(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    public void updateActive(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody Map<String, String> keyword);
    public void updateInactive(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody Map<String, String> keyword);
    public List<MemberDTO> find(@AuthenticationPrincipal CustomUserDetails customUserDetails);
}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage/**")
@RequiredArgsConstructor
public class MyPageRestController {
    private final MemberService memberService;

    @GetMapping("/request-list")
    public ResponseEntity<?> getRequests(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<RequestExperienceDTO> req=memberService.findRequestExperienceByMemberId(memberId);
        if(req.size()!=0){
            return ResponseEntity.ok(req);
        }
        return ResponseEntity.notFound().build();
    }
}

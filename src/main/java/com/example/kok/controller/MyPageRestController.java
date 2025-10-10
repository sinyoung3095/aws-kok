package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage/**")
@RequiredArgsConstructor
public class MyPageRestController {
//    @GetMapping("/request-list")
//    public ResponseEntity<?> getRequests(@AuthenticationPrincipal CustomUserDetails customUserDetails){
//        long memberId=customUserDetails.getId();
//    }
}

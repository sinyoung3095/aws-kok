package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PaymentDTO;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestInternDTO;
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

    @GetMapping("/intern-list")
    public ResponseEntity<?> getInterns(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<RequestInternDTO> req=memberService.findRequestInternByMemberId(memberId);
        if(req.size()!=0){
            return ResponseEntity.ok(req);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/post-list")
    public ResponseEntity<?> getPosts(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<PostDTO> posts=memberService.findPostsByMemberId(memberId);
        if(posts.size()!=0){
            return ResponseEntity.ok(posts);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/payment-list")
    public ResponseEntity<?> getPayments(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<PaymentDTO> payments=memberService.findPaymentByMemberId(memberId);
        if(payments.size()!=0){
            return ResponseEntity.ok(payments);
        }
        return ResponseEntity.notFound().build();
    }
}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/mypage/**")
@RequiredArgsConstructor
public class MyPageRestController {
    private final MemberService memberService;
    private final ExperienceNoticeService experienceNoticeService;

//    저장한 체험 공고
    @GetMapping("/saved-exp")
    public ResponseEntity getSavedExp(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        long memberId=customUserDetails.getId();
        List<ExperienceNoticeDTO> list=memberService.findExperienceNoticeByMemberId(memberId);
        if(list.size()!=0){
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

    //    저장한 인턴 공고
    @GetMapping("/saved-int")
    public ResponseEntity getSavedInt(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        long memberId=customUserDetails.getId();
        List<InternNoticeDTO> list=memberService.findInternNoticeByMemberId(memberId);
        if(list.size()!=0){
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

//    지원한 체험 공고
    @GetMapping("/request-list")
    public ResponseEntity<?> getRequests(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<RequestExperienceDTO> req=memberService.findRequestExperienceByMemberId(memberId);
        if(req.size()!=0){
            return ResponseEntity.ok(req);
        }
        return ResponseEntity.notFound().build();
    }

//    체험 지원 취소
    @DeleteMapping("/delete-request/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable("requestId") Long requestId) {
        memberService.deleteRequestExperience(requestId);
        return ResponseEntity.ok().build();
    }

//    지원한 인턴 공고
    @GetMapping("/intern-list")
    public ResponseEntity<?> getInterns(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<RequestInternDTO> req=memberService.findRequestInternByMemberId(memberId);
        if(req.size()!=0){
            return ResponseEntity.ok(req);
        }
        return ResponseEntity.notFound().build();
    }

    //    인턴 지원 취소
    @DeleteMapping("/delete-intern/{requestId}")
    public ResponseEntity<?> deleteIntern(@PathVariable("requestId") Long requestId) {
        memberService.deleteRequestIntern(requestId);
        return ResponseEntity.ok().build();
    }

//    게시글
    @GetMapping("/post-list")
    public ResponseEntity<?> getPosts(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<PostDTO> posts=memberService.findPostsByMemberId(memberId);
        if(posts.size()!=0){
            return ResponseEntity.ok(posts);
        }
        return ResponseEntity.notFound().build();
    }

//    결제 내역
    @GetMapping("/payment-list")
    public ResponseEntity<?> getPayments(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<PaymentDTO> payments=memberService.findPaymentByMemberId(memberId);
        if(payments.size()!=0){
            return ResponseEntity.ok(payments);
        }
        return ResponseEntity.notFound().build();
    }

//    프로필 편집 완료
    @PostMapping("/profile-update")
    public void updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @RequestParam(value="profileImgInput", required=false) MultipartFile file,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="job", required=false) String job,
                              @RequestParam(value="info", required=false) String info){
        long memberId=customUserDetails.getId();
        UserMemberDTO member=new UserMemberDTO();
        member.setId(memberId);
        member.setUserName(name);
        member.setJobName(job);
        member.setMemberInfo(info);
        memberService.updateProfile(memberId, member, file);
    }
}

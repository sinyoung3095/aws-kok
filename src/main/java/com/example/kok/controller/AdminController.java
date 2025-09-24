package com.example.kok.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/**")
@RequiredArgsConstructor
public class AdminController {

//    관리자 등록
    @GetMapping("join")
    public String goToJoinPage() {
        return "admin/join";
    }

//    관리자 로그인
    @GetMapping("login")
    public String goToLoginPage() {
        return "admin/login";
    }

//    메인 페이지
    @GetMapping("")
    public String goToMainPagePage() {
        return "admin/main-page";
    }

//    회원관리 - 일반회원
    @GetMapping("member")
    public String goToMemberPage() {
        return "admin/member";
    }

//    회원관리 - 기업회원
    @GetMapping("company")
    public String goToCompanyPage() {
        return "admin/company";
    }

//    체험
    @GetMapping("experience")
    public String goToExperiencePage() {
        return "admin/experience";
    }

//    인턴공고
    @GetMapping("employ")
    public String goToEmployPage() {
        return "admin/employ";
    }

//    배너 - 광고 신청
    @GetMapping("advertise")
    public String goToAdvertisePage() {
        return "admin/advertise";
    }

//    배너 - 현수막
    @GetMapping("banner")
    public String goToBannerPage() {
        return "admin/banner";
    }

//    결제 - 광고
    @GetMapping("paymentAdvertise")
    public String goToPaymentAdvertisePage() {
        return "admin/payment-advertise";
    }

//    결제 - 체험비
    @GetMapping("paymentExperience")
    public String goToPaymentExperiencePage() {
        return "admin/payment-experience";
    }

//    신고 게시글
    @GetMapping("notifyPost")
    public String goToNotifyPostPage() {
        return "admin/notify-post";
    }

//    고객지원 - 공지사항
    @GetMapping("support")
    public String goToSupportPage() {
        return "admin/support";
    }

//    고객지원 - 공지사항 상세
    @GetMapping("supportDetail")
    public String goToSupportDetailPage() {
        return "admin/support-detail";
    }

//    고객지원 - 공지사항 수정
    @GetMapping("supportUpdate")
    public String goToSupportUpdatePage() {
        return "admin/support-update";
    }

//    고객지원 - 공지사항 등록
    @GetMapping("supportWrite")
    public String goToSupportWritePage() {
        return "admin/support-write";
    }
}

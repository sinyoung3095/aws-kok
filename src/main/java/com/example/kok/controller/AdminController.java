package com.example.kok.controller;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.dto.BannerFileDTO;
import com.example.kok.service.AdminAdvertisementService;
import com.example.kok.service.AdminBannerService;
import com.example.kok.service.AdminReportService;
import com.example.kok.service.AdminService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/**")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final AdminReportService adminReportService;
    private final AdminAdvertisementService adminAdvertisementService;
    private final AdminBannerService adminBannerService;

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
    public String goToAdvertisePage(Model model) {
        return "admin/advertise";
    }
    @GetMapping("advertise/accept/{id}")
    public RedirectView advertisementAccept(@PathVariable Long id) {
        adminAdvertisementService.accept(id);
        return new RedirectView("/admin/advertise");
    }
    @GetMapping("advertise/reject/{id}")
    public RedirectView advertisementReject(@PathVariable Long id) {
        adminAdvertisementService.reject(id);
        return new RedirectView("/admin/advertise");
    }

//    배너 - 현수막
    @GetMapping("banner")
    public String goToBannerPage() {
        return "admin/banner";
    }
    @PostMapping("banner/save")
    public RedirectView saveBanner(@RequestParam(value = "file", required = false) List<MultipartFile> multipartFiles) {
        log.info("저장 컨트롤러 들어옴");
        adminBannerService.save(multipartFiles);
        return new RedirectView("/admin/banner");
    }
    @PostMapping("banner/delete/{id}")
    public RedirectView deleteBanner(@PathVariable Long id) {
        adminBannerService.delete(id);
        return new RedirectView("/admin/banner");
    }

//    결제 - 광고
    @GetMapping("payment/advertise")
    public String goToPaymentAdvertisePage(Model model) {
        return "admin/payment-advertise";
    }

//    결제 - 체험비
    @GetMapping("payment/experience")
    public String goToPaymentExperiencePage() {
        return "admin/payment-experience";
    }

//    신고 게시글
    @GetMapping("notify/post")
    public String goToNotifyPostPage() {
        return "admin/notify-post";
    }

//    신고 게시글 삭제
    @GetMapping("notify/post/delete/{id}")
    public RedirectView notifyPostDelete(@PathVariable Long id) {
        adminReportService.deleteReportPost(id);
        return new RedirectView("/admin/notify/post");
    }

//    고객지원 - 공지사항 목록
    @GetMapping("support")
    public String goToSupportPage() {
        return "admin/support";
    }

//    고객지원 - 공지사항 상세
    @GetMapping("support/detail/{id}")
    public String goToSupportDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("notice", adminService.getNotice(id).orElseThrow(PostNotFoundException::new));
        log.info("noticeModel: {}", model);
        return "admin/support-detail";
    }

//    고객지원 - 공지사항 수정
    @GetMapping("support/update/{id}")
    public String goToSupportUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("adminNotice", adminService.getNotice(id).orElseThrow(PostNotFoundException::new));
        return "admin/support-update";
    }

    @PostMapping("support/update")
    public RedirectView update(AdminNoticeDTO adminNoticeDTO) {
        adminService.update(adminNoticeDTO);
        return new RedirectView("/admin/support/detail/" + adminNoticeDTO.getId());
    }

//    고객지원 - 공지사항 등록
    @GetMapping("support/write")
    public String goToSupportWritePage(AdminNoticeDTO adminNoticeDTO, Model model) {
        model.addAttribute("adminNotice", adminNoticeDTO);
        return "admin/support-write";
    }

    @PostMapping("support/write")
    public RedirectView write(AdminNoticeDTO adminNoticeDTO) {
        adminService.write(adminNoticeDTO);
        return new RedirectView("/admin/support/detail/" + adminNoticeDTO.getId());
    }

//    고객지원 - 공지사항 삭제
    @GetMapping("support/delete/{id}")
    public RedirectView delete (@PathVariable Long id) {
        adminService.delete(id);
        return new RedirectView("/admin/support");
    }
}

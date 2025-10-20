package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.service.ConsoleInternApplicationService;
import com.example.kok.service.ConsoleInternDetailService;
import com.example.kok.service.ConsoleInternNoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/intern")
@RequiredArgsConstructor
public class ConsoleInternNoticeController {
    private final ConsoleInternNoticeService consoleInternNoticeService;
    private final ConsoleInternDetailService consoleInternDetailService;
    private final ConsoleInternApplicationService consoleInternApplicationService;

    //    기업 콘솔 인턴 공고 목록
    @GetMapping("/list")
    public String goToList(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           Model model) {

        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("companyId", companyId);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return "enterprise-console/intern/console-intern-list";
    }

    //    기업 콘솔 인턴 공고 등록, 수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(@PathVariable(required = false) Long id,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails,
                            HttpServletRequest request,
                            Model model) {

        ConsoleInternListRequestDTO notice = consoleInternNoticeService.getNotice(id);

        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleInternListRequestDTO());
            model.addAttribute("companyId", companyId);
            model.addAttribute("companyName", companyName);
            model.addAttribute("memberName", memberName);

            return "enterprise-console/intern/console-intern-update";
        }

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        model.addAttribute("companyId", companyId);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return "enterprise-console/console-intern-update";
    }


//    기업 콘솔 인턴 지원서 목록
    @GetMapping("/applicate-list/{internNoticeId}")
    public String goToApplicateList(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                    @PathVariable("internNoticeId") Long internNoticeId,
                                    Model model) {

        ConsoleInternListDTO internDetail = consoleInternDetailService.getDetail(internNoticeId);

        List<ConsoleInternApplicantDTO> applicants  =
                consoleInternApplicationService.getApplicantsByNoticeId(internNoticeId);

        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("internDetail", internDetail);
        model.addAttribute("internNoticeId", internNoticeId);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        if (applicants != null && !applicants.isEmpty()) {//지원자 id
            ConsoleInternApplicantDTO firstApplicant = applicants.get(0);
            model.addAttribute("memberId", firstApplicant.getUserId());
        }

        return "enterprise-console/intern/console-intern-applicate-list";
    }

//    기업 콘솔 인턴 지원서
    @GetMapping("/application/{internNoticeId}/{memberId}")
    public String goToApplication(@PathVariable("internNoticeId") Long internNoticeId,
                                  @PathVariable("memberId") Long memberId,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  Model model) {

        ConsoleInternApplicantDTO applicantDetail =
                consoleInternApplicationService.getApplicantDetail(memberId, internNoticeId);

        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("applicantDetail", applicantDetail);
        model.addAttribute("memberId", memberId); //지원자 id
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return "enterprise-console/intern/console-intern-application";
    }

}

package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.dto.ConsoleExperienceListRequestDTO;
import com.example.kok.service.ConsoleExperienceApplicationService;
import com.example.kok.service.ConsoleExperienceDetailService;
import com.example.kok.service.ConsoleExperienceListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/experience")
@RequiredArgsConstructor
public class ConsoleExperienceController {
    private final ConsoleExperienceListService consoleExperienceListService;
    private final ConsoleExperienceDetailService consoleExperienceDetailService;
    private final ConsoleExperienceApplicationService consoleExperienceApplicationService;

//    기업 콘솔 체험 공고 목록
    @GetMapping("/list")
    public String goToList(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        Long companyId = customUserDetails.getId();

        model.addAttribute("companyId", companyId);
        model.addAttribute("userDTO", customUserDetails);

        return "enterprise-console/console-experience-list";
    }

//    기업 콘솔 체험 공고 등록, 수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(HttpServletRequest request,
                            @PathVariable(required = false) Long id,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails,
                            Model model) {

        if(request.getRequestURI().contains("create")){

            Long companyId = customUserDetails.getId();

            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleExperienceListRequestDTO());
            model.addAttribute("companyId", companyId);

            return "enterprise-console/console-experience-update";
        }

        ConsoleExperienceListRequestDTO notice = consoleExperienceListService.getNotice(id);

        Long companyId = customUserDetails.getId();

        model.addAttribute("userDTO", customUserDetails);
        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        model.addAttribute("companyId", companyId);
        return "enterprise-console/console-experience-update";
    }

//    기업 콘솔 체험 지원서 목록
    @GetMapping("/applicate-list/{experienceNoticeId}")
    public String goToApplicateList(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @PathVariable("experienceNoticeId") Long experienceNoticeId,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                    Model model) {

        ConsoleExperienceListDTO experienceDetail = consoleExperienceDetailService.getDetail(experienceNoticeId);

        List<ConsoleExperienceApplicantDTO> applicants  =
                consoleExperienceApplicationService.getApplicantsByNoticeId(experienceNoticeId);

        model.addAttribute("userDTO", customUserDetails);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("experienceDetail", experienceDetail);
        model.addAttribute("experienceNoticeId", experienceNoticeId);
        model.addAttribute("memberId", applicants.get(0).getUserId()); //지원자 id
        return "enterprise-console/console-experience-applicate-list";
    }


//    기업 콘솔 체험 지원서
    @GetMapping("/application/{experienceNoticeId}/{memberId}")
    public String goToApplication(@PathVariable("experienceNoticeId") Long experienceNoticeId,
                                  @PathVariable("memberId") Long memberId,
                                  @AuthenticationPrincipal CustomUserDetails userDetails,
                                  Model model) {

        ConsoleExperienceApplicantDTO applicantDetail =
                consoleExperienceApplicationService.getApplicantDetail(memberId, experienceNoticeId);

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("applicantDetail", applicantDetail);
        model.addAttribute("memberId", memberId); //지원자 id


        return "enterprise-console/console-experience-application";
    }

//    기업 콘솔 평가서
    @GetMapping("/review")
    public String goToReview() {
        return "enterprise-console/console-review";
    }


}

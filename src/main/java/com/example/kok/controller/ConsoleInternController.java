package com.example.kok.controller;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.ConsoleInternListDTO;
import com.example.kok.dto.ConsoleInternListRequestDTO;
import com.example.kok.service.ConsoleInternApplicationService;
import com.example.kok.service.ConsoleInternDetailService;
import com.example.kok.service.ConsoleInternListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/intern")
@RequiredArgsConstructor
public class ConsoleInternController {
    private final ConsoleInternListService consoleInternListService;
    private final ConsoleInternDetailService consoleInternDetailService;
    private final ConsoleInternApplicationService consoleInternApplicationService;

    //    기업 콘솔 인턴 공고 목록
    @GetMapping("/list")
    public String goToList() {
        return "enterprise-console/console-intern-list";
    }

    //    기업 콘솔 인턴 공고 등록, 수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(HttpServletRequest request, Model model, @PathVariable(required = false) Long id) {
        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleInternListRequestDTO());
            return "enterprise-console/console-intern-update";
        }
        ConsoleInternListRequestDTO notice = consoleInternListService.getNotice(id);

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        return "enterprise-console/console-intern-update";
    }


//    기업 콘솔 인턴 지원서 목록
    @GetMapping("/applicate-list/{internNoticeId}")
    public String goToApplicateList(Model model,
                                    @PathVariable("internNoticeId") Long internNoticeId) {

        ConsoleInternListDTO internDetail = consoleInternDetailService.getDetail(internNoticeId);

        model.addAttribute("internDetail", internDetail);
        model.addAttribute("internNoticeId", internNoticeId);
        return "enterprise-console/console-intern-applicate-list";
    }


//    기업 콘솔 인턴 지원서
    @GetMapping("/application/{internNoticeId}/{memberId}")
    public String goToApplication(@PathVariable("internNoticeId") Long internNoticeId,
                                  @PathVariable("memberId") Long memberId,
                                  Model model) {

        ConsoleInternApplicantDTO applicantDetail =
                consoleInternApplicationService.getApplicantDetail(memberId, internNoticeId);

        model.addAttribute("applicantDetail", applicantDetail);

        return "enterprise-console/console-intern-application";
    }

    //    기업 콘솔 인턴 지원서
    @GetMapping("/application")
    public String goToApplication() {
        return "enterprise-console/console-intern-application";
    }

}

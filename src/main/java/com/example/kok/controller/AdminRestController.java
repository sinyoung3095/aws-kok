package com.example.kok.controller;

import com.example.kok.dto.*;
import com.example.kok.service.AdminAdvertisementService;
import com.example.kok.service.AdminBannerService;
import com.example.kok.service.AdminReportService;
import com.example.kok.service.AdminService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/**")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;
    private final AdminReportService adminReportService;
    private final AdminAdvertisementService adminAdvertisementService;
    private final AdminBannerService adminBannerService;

//    공지 목록
    @GetMapping("support/{page}")
    public AdminNoticeCriteriaDTO list(@PathVariable("page") int page){
        log.info("page = {}", page);
        return adminService.getList(page);
    }

//    체험 목록
    @GetMapping("experience/{page}")
    public AdminExperienceListDTO getExperience(@PathVariable("page") int page,
                                                @RequestParam(required = false) String keyword){
        log.info("page = {}", page);
        log.info("search = {}", keyword);
        return adminService.getExperience(page, keyword);
    }

//    체험 상세
    @GetMapping("experience/detail/{id}/{page}")
    public AdminExperienceDetailDTO getExperienceDetail(@PathVariable("id")Long id, @PathVariable("page")int page){
        log.info("page = {}", page);
        log.info("id = {}", id);
        return adminService.getExperienceDetail(page, id);
    }

//    신고 게시글 목록
    @GetMapping("notify/post/{page}")
    public AdminReportCriteriaDTO getNotifyList(@PathVariable("page") int page){
        log.info("page = {}", page);
        return adminReportService.getReportList(page);
    }

//    신고 게시글 상세
    @GetMapping("notify/post/detail/{id}")
    public AdminReportDTO getNotifyDetail(@PathVariable("id")Long id){
        log.info("id = {}", id);
        return adminReportService.getReportDetail(id);
    }

//    광고 목록
    @GetMapping("advertise/{page}")
    public AdminAdvertisementCriteriaDTO getAdvertisementList(@PathVariable("page") int page,
                                                              @RequestParam(required = false) String keyword,
                                                              @RequestParam(required = false) String category){
        return adminAdvertisementService.advertisementList(page, keyword, category);
    }

//    광고 상세
    @GetMapping("advertise/detail/{id}")
    public AdminAdvertisementDTO getAdvertisementDetail(@PathVariable("id")Long id){
        log.info("id = {}", id);
        return adminAdvertisementService.advertisementDetail(id);
    }

//    현수막
    @GetMapping("banner/{page}")
    public BannerFileCriteriaDTO getBanner(@PathVariable("page") int page, Long id){
        log.info("page = {}", page);
//        BannerFileDTO bannerFileDTO = adminBannerService.selectAllFiles(id);
//        adminBannerService.setPreSignedUrl(bannerFileDTO);
        return adminBannerService.bannerList(page);
    }
}

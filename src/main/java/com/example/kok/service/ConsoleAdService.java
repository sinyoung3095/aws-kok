package com.example.kok.service;

import com.example.kok.dto.ConsoleAdNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.dto.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.util.List;

public interface ConsoleAdService {
//    광고 목록
    public ConsoleAdNoticeCriteriaDTO getList(Long companyId, int page, String keyword);

//    광고 상세
    public ConsoleAdNoticeDTO getDetail(Long advertisementId);
    public void setPreSignedUrl(ConsoleAdNoticeDTO consoleAdNoticeDTO);

//    광고 등록
    public void registerAdvertisement(ConsoleAdNoticeDTO adNoticeDTO, List<MultipartFile> multipartFiles);

//    광고 수정 등록
    public void modifyNotice(ConsoleAdNoticeDTO adNoticeDTO, List<MultipartFile> multipartFiles);

//    광고 수정 상세
    public ConsoleAdNoticeDTO getNotice(Long advertisementId);

}

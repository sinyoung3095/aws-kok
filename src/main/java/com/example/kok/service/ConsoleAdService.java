package com.example.kok.service;

import com.example.kok.dto.ConsoleAdNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;

import java.io.Console;

public interface ConsoleAdService {
//    광고 목록
    public ConsoleAdNoticeCriteriaDTO getList(Long companyId, int page, String keyword);

//    공고 상세
    ConsoleAdNoticeDTO getDetail(Long id);

//    광고 등록
    public void registerAdvertisement(ConsoleAdNoticeDTO adNoticeDTO);

//    광고 수정 등록
    public void modifyNotice(ConsoleAdNoticeDTO adNoticeDTO);

//    광고 수정 상세
    ConsoleAdNoticeDTO getNotice(Long id);

//    public void setPreSignedUrl(PostDTO postDTO);
}

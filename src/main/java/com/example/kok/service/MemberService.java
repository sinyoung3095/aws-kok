package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {
    public void joinMember(MemberVO memberVO);

//    보관함에 이력서 넣기
    public void putFileAtStorage(List<MultipartFile> file, Long memberId);

//    이력서들 조회
    public List<FileDTO> findFilesByMemberId(Long memberId);
}

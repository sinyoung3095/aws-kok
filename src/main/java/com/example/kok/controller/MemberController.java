package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.domain.MemberVO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.service.MemberService;
import com.example.kok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/member/**")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final UserService userService;

//    보관함에 파일 등록하기
    @PostMapping("/storage/save")
    public void saveStorageFile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                @RequestParam(value="files") List<MultipartFile> files){
        Long memberId = customUserDetails.getId();
        memberService.putFileAtStorage(files, memberId);
    }

//    보관함 목록 열람
    @GetMapping("/storage/load")
    public List<FileDTO> loadStorageFile(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long memberId = customUserDetails.getId();
        List<FileDTO> files=memberService.findFilesByMemberId(memberId);
        return files;
    }
}

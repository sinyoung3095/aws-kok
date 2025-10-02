package com.example.kok.controller;

import com.example.kok.dto.UserMemberDTO;
import com.example.kok.service.MemberService;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/member/")
@RequiredArgsConstructor
public class AdminMembersApiController {
//    @Autowired
//    private MemberService memberService;
//
//    @GetMapping("list/{page}")
//    public List<UserMemberDTO> findUserMembers(Criteria criteria, String keyword) {
//        re
//    }

}

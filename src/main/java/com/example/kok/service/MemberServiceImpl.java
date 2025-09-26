package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    @Override
    public void joinMember(MemberVO memberVO) {
        memberDAO.saveMember(memberVO);
    }
}
